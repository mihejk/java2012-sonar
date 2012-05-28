package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	private static double VAR99_STANDARD = 2.32634787404;
	private static double VAR95_STANDARD = 1.64485362695;
	private static double DAMPING_FACTOR = 0.91;

	@Autowired
	private PositionService positionService;

	@Autowired
	private PriceService priceService;

	@Override
	public double portfolioValue(Portfolio portfolio) {
		List<Position> positions = positionService.findByPortfolio(portfolio);
		double totalValue = 0.0;
		for (Position position : positions) {
			Price price = priceService.findLastPrice(position.getStock());
			totalValue += position.getQuantity() * price.getValue();
		}
		return totalValue;
	}

	@Override
	public double exactVar95(Portfolio portfolio) {
		List<Position> positions = positionService.findByPortfolio(portfolio);
		List<Price> prices = new ArrayList<Price>();
		double totalValue = 0.0;
		for (Position position : positions) {
			Price price = priceService.findLastPrice(position.getStock());
			prices.add(price);
			totalValue += position.getQuantity() * price.getValue();
		}
		double stdDev = 0.0;
		for (int i = 0; i < positions.size(); ++i) {
			Position position = positions.get(i);
			Price price = prices.get(i);
			double value = position.getQuantity() * price.getValue();
			double weight = value / totalValue;
			stdDev += weight * weight * position.getStock().getStdDev() * position.getStock().getStdDev();
		}
		stdDev = Math.sqrt(stdDev);

		double var = VAR95_STANDARD * stdDev;
		return var;
	}

	@Override
	public double exactVar99(Portfolio portfolio) {
		List<Position> positions = positionService.findByPortfolio(portfolio);
		List<Price> prices = new ArrayList<Price>();
		double totalValue = 0.0;
		for (Position position : positions) {
			Price price = priceService.findLastPrice(position.getStock());
			prices.add(price);
			totalValue += position.getQuantity() * price.getValue();
		}
		double stdDev = 0.0;
		for (int i = 0; i < positions.size(); ++i) {
			Position position = positions.get(i);
			Price price = prices.get(i);
			double value = position.getQuantity() * price.getValue();
			double weight = value / totalValue;
			stdDev += weight * weight * position.getStock().getStdDev() * position.getStock().getStdDev();

		}
		stdDev = Math.sqrt(stdDev);

		double var = VAR99_STANDARD * stdDev;
		return var;
	}

	@Override
	public double historicVar95(Portfolio portfolio) {
		List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		List<Double> historicValues = new ArrayList<Double>();
		List<Double> historicReturns = new ArrayList<Double>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (Position position : positions) {
				double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				historicReturns.add(historicReturn);
				mean += historicReturn;
			}
		}
		mean /= (priceNum - 1);

		Collections.sort(historicReturns);

		// A dummy sentinel
		historicReturns.add(Double.NaN);
		double percentile = 0.95;
		double position = (1.0 - percentile) * (priceNum - 2);
		int index = (int) Math.floor(position);

		double meanAdjustedReturn = historicReturns.get(index) - mean;
		double meanAdjustedReturn1 = historicReturns.get(index + 1) - mean;
		double var = -((index + 1 - position) * meanAdjustedReturn + (position - index) * meanAdjustedReturn1);

		return var;
	}

	@Override
	public double historicVar99(Portfolio portfolio) {
		List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		List<Double> historicValues = new ArrayList<Double>();
		List<Double> historicReturns = new ArrayList<Double>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (Position position : positions) {
				double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				historicReturns.add(historicReturn);
				mean += historicReturn;
			}
		}
		mean /= (priceNum - 1);

		Collections.sort(historicReturns);

		// A dummy sentinel
		historicReturns.add(Double.NaN);
		double percentile = 0.99;
		double position = (1.0 - percentile) * (priceNum - 2);
		int index = (int) Math.floor(position);

		double meanAdjustedReturn = historicReturns.get(index) - mean;
		double meanAdjustedReturn1 = historicReturns.get(index + 1) - mean;
		double var = -((index + 1 - position) * meanAdjustedReturn + (position - index) * meanAdjustedReturn1);

		return var;
	}

	@Override
	public double hybridVar95(Portfolio portfolio) {
		List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		List<Double> historicValues = new ArrayList<Double>();
		List<WeightedReturn> historicWeightedReturns = new ArrayList<WeightedReturn>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (Position position : positions) {
				double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				double weight = (1.0 - DAMPING_FACTOR) * Math.pow(DAMPING_FACTOR, i - 1) / (1.0 - Math.pow(DAMPING_FACTOR, priceNum - 1));
				historicWeightedReturns.add(new WeightedReturn(weight, historicReturn));
				mean += weight * historicReturn;
			}
		}

		Collections.sort(historicWeightedReturns, new WeightedReturnComparator());

		double percentile = 0.95;
		// Best initial guess
		WeightedReturn previousWeightedReturn = historicWeightedReturns.get(0);
		double previousCummulativeWeight = 0.0;
		double var = 0.0;
		for (int i = 0; i < priceNum - 1; ++i) {
			WeightedReturn weightedReturn = historicWeightedReturns.get(i);
			double cummulativeWeight = previousCummulativeWeight + weightedReturn.getWeight();
			if (cummulativeWeight >= 1.0 - percentile) {
				double meanAdjustedPreviousReturn = previousWeightedReturn.getReturn() - mean;
				double meanAdjustedReturn = weightedReturn.getReturn() - mean;
				var = -(meanAdjustedPreviousReturn * (cummulativeWeight - (1.0 - percentile)) + meanAdjustedReturn
						* (1.0 - percentile - previousCummulativeWeight))
						/ weightedReturn.getWeight();
				break;
			}
			previousWeightedReturn = weightedReturn;
			previousCummulativeWeight = cummulativeWeight;
		}

		return var;
	}

	@Override
	public double hybridVar99(Portfolio portfolio) {
		List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		List<Double> historicValues = new ArrayList<Double>();
		List<WeightedReturn> historicWeightedReturns = new ArrayList<WeightedReturn>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (Position position : positions) {
				double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				double weight = (1.0 - DAMPING_FACTOR) * Math.pow(DAMPING_FACTOR, i - 1) / (1.0 - Math.pow(DAMPING_FACTOR, priceNum - 1));
				historicWeightedReturns.add(new WeightedReturn(weight, historicReturn));
				mean += weight * historicReturn;
			}
		}

		Collections.sort(historicWeightedReturns, new WeightedReturnComparator());

		double percentile = 0.99;
		// Best initial guess
		WeightedReturn previousWeightedReturn = historicWeightedReturns.get(0);
		double previousCummulativeWeight = 0.0;
		double var = 0.0;
		for (int i = 0; i < priceNum - 1; ++i) {
			WeightedReturn weightedReturn = historicWeightedReturns.get(i);
			double cummulativeWeight = previousCummulativeWeight + weightedReturn.getWeight();
			if (cummulativeWeight >= 1.0 - percentile) {
				double meanAdjustedPreviousReturn = previousWeightedReturn.getReturn() - mean;
				double meanAdjustedReturn = weightedReturn.getReturn() - mean;
				var = -(meanAdjustedPreviousReturn * (cummulativeWeight - (1.0 - percentile)) + meanAdjustedReturn
						* (1.0 - percentile - previousCummulativeWeight))
						/ weightedReturn.getWeight();
				break;
			}
			previousWeightedReturn = weightedReturn;
			previousCummulativeWeight = cummulativeWeight;
		}

		return var;
	}

	private class WeightedReturn {

		private double weight;
		private double r3turn;

		public WeightedReturn(double weight, double r3turn) {
			this.weight = weight;
			this.r3turn = r3turn;
		}

		public double getWeight() {
			return weight;
		}

		public double getReturn() {
			return r3turn;
		}

	}

	private class WeightedReturnComparator implements Comparator<WeightedReturn> {

		@Override
		public int compare(WeightedReturn firstWeightedReturn, WeightedReturn secondWeightedReturn) {
			return (int) Math.signum(firstWeightedReturn.getReturn() - secondWeightedReturn.getReturn());
		}

	}

}
