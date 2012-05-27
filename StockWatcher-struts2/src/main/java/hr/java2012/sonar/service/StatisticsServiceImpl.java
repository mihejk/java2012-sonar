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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceImpl.class);

	private static final double VAR99_STANDARD = 2.32634787404;
	private static final double VAR95_STANDARD = 1.64485362695;
	private static final double DAMPING_FACTOR = 0.91;

	@Autowired
	private PositionService positionService;

	@Autowired
	private PriceService priceService;

	@Override
	public double exactVar95(final Portfolio portfolio) {
		LOGGER.debug("Exact VaR95 calculation for {}", portfolio.getName());

		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final List<Price> prices = new ArrayList<Price>();
		double totalValue = 0.0;
		for (final Position position : positions) {
			final Price price = priceService.findLastPrice(position.getStock());
			prices.add(price);
			totalValue += position.getQuantity() * price.getValue();
		}
		double stdDev = 0.0;
		for (int i = 0; i < positions.size(); ++i) {
			final Position position = positions.get(i);
			final Price price = prices.get(i);
			final double value = position.getQuantity() * price.getValue();
			final double weight = value / totalValue;
			stdDev += weight * weight * position.getStock().getStdDev() * position.getStock().getStdDev();
			LOGGER.debug("\tPosition: {} Weight: {} Quantity: {} Price: {} Value: {}",
					new Object[] { position.getStock().getTicker(), weight, position.getQuantity(), price.getValue(), value });
		}
		stdDev = Math.sqrt(stdDev);

		final double var = VAR95_STANDARD * stdDev;
		LOGGER.debug("Total: Value: {} StdDev: {} VaR95: {}", new Object[] { totalValue, stdDev, var });

		return var;
	}

	@Override
	public double exactVar99(final Portfolio portfolio) {
		LOGGER.debug("Exact VaR99 calculation for {}", portfolio.getName());

		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final List<Price> prices = new ArrayList<Price>();
		double totalValue = 0.0;
		for (final Position position : positions) {
			final Price price = priceService.findLastPrice(position.getStock());
			prices.add(price);
			totalValue += position.getQuantity() * price.getValue();
		}
		double stdDev = 0.0;
		for (int i = 0; i < positions.size(); ++i) {
			final Position position = positions.get(i);
			final Price price = prices.get(i);
			final double value = position.getQuantity() * price.getValue();
			final double weight = value / totalValue;
			stdDev += weight * weight * position.getStock().getStdDev() * position.getStock().getStdDev();
			LOGGER.debug("\tPosition: {} Weight: {} Quantity: {} Price: {} Value: {}",
					new Object[] { position.getStock().getTicker(), weight, position.getQuantity(), price.getValue(), value });
		}
		stdDev = Math.sqrt(stdDev);

		final double var = VAR99_STANDARD * stdDev;
		LOGGER.debug("Total: Value: {} StdDev: {} VaR99: {}", new Object[] { totalValue, stdDev, var });

		return var;
	}

	@Override
	public double historicVar95(final Portfolio portfolio) {
		LOGGER.debug("VaR95 calculation via historic method for {}", portfolio.getName());

		final List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (final Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		LOGGER.debug("Using {} prices.", priceNum);

		final Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (final Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		final List<Double> historicValues = new ArrayList<Double>();
		final List<Double> historicReturns = new ArrayList<Double>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (final Position position : positions) {
				final double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				final double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				historicReturns.add(historicReturn);
				mean += historicReturn;
			}
		}
		mean /= (priceNum - 1);

		Collections.sort(historicReturns);

		// A dummy sentinel
		historicReturns.add(Double.NaN);
		final double percentile = 0.95;
		final double position = (1.0 - percentile) * (priceNum - 2);
		final int index = (int) Math.floor(position);

		final double meanAdjustedReturn = historicReturns.get(index) - mean;
		final double meanAdjustedReturn1 = historicReturns.get(index + 1) - mean;
		final double var = -((index + 1 - position) * meanAdjustedReturn + (position - index) * meanAdjustedReturn1);
		LOGGER.debug("VaR95: {}", var);

		return var;
	}

	@Override
	public double historicVar99(final Portfolio portfolio) {
		LOGGER.debug("VaR99 calculation via historic method for {}", portfolio.getName());

		final List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (final Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		LOGGER.debug("Using {} prices.", priceNum);

		final Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (final Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		final List<Double> historicValues = new ArrayList<Double>();
		final List<Double> historicReturns = new ArrayList<Double>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (final Position position : positions) {
				final double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				final double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				historicReturns.add(historicReturn);
				mean += historicReturn;
			}
		}
		mean /= (priceNum - 1);

		Collections.sort(historicReturns);

		// A dummy sentinel
		historicReturns.add(Double.NaN);
		final double percentile = 0.99;
		final double position = (1.0 - percentile) * (priceNum - 2);
		final int index = (int) Math.floor(position);

		final double meanAdjustedReturn = historicReturns.get(index) - mean;
		final double meanAdjustedReturn1 = historicReturns.get(index + 1) - mean;
		final double var = -((index + 1 - position) * meanAdjustedReturn + (position - index) * meanAdjustedReturn1);
		LOGGER.debug("VaR99: {}", var);

		return var;
	}

	@Override
	public double hybridVar95(final Portfolio portfolio) {
		LOGGER.debug("VaR95 calculation via hybrid method for {}", portfolio.getName());

		final List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (final Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		LOGGER.debug("Using {} prices.", priceNum);

		final Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (final Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		final List<Double> historicValues = new ArrayList<Double>();
		final List<WeightedReturn> historicWeightedReturns = new ArrayList<WeightedReturn>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (final Position position : positions) {
				final double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				final double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				final double weight = (1.0 - DAMPING_FACTOR) * Math.pow(DAMPING_FACTOR, i - 1) / (1.0 - Math.pow(DAMPING_FACTOR, priceNum - 1));
				historicWeightedReturns.add(new WeightedReturn(weight, historicReturn));
				mean += weight * historicReturn;
			}
		}

		Collections.sort(historicWeightedReturns, new WeightedReturnComparator());

		final double percentile = 0.95;
		// Best initial guess
		WeightedReturn previousWeightedReturn = historicWeightedReturns.get(0);
		double previousCummulativeWeight = 0.0;
		double var = 0.0;
		for (int i = 0; i < priceNum - 1; ++i) {
			final WeightedReturn weightedReturn = historicWeightedReturns.get(i);
			final double cummulativeWeight = previousCummulativeWeight + weightedReturn.getWeight();
			if (cummulativeWeight >= 1.0 - percentile) {
				final double meanAdjustedPreviousReturn = previousWeightedReturn.getReturn() - mean;
				final double meanAdjustedReturn = weightedReturn.getReturn() - mean;
				var = -(meanAdjustedPreviousReturn * (cummulativeWeight - (1.0 - percentile)) + meanAdjustedReturn
						* (1.0 - percentile - previousCummulativeWeight))
						/ weightedReturn.getWeight();
				break;
			}
			previousWeightedReturn = weightedReturn;
			previousCummulativeWeight = cummulativeWeight;
		}

		LOGGER.debug("VaR95: {}", var);

		return var;
	}

	@Override
	public double hybridVar99(final Portfolio portfolio) {
		LOGGER.debug("VaR99 calculation via hybrid method for {}", portfolio.getName());

		final List<Position> positions = positionService.findByPortfolio(portfolio);

		int priceNum = 0;
		for (final Position position : positions) {
			priceNum = Math.max(priceNum, (int) priceService.countPrices(position.getStock()));
		}

		LOGGER.debug("Using {} prices.", priceNum);

		final Map<Position, List<Price>> historicPrices = new HashMap<Position, List<Price>>();
		for (final Position position : positions) {
			historicPrices.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}

		final List<Double> historicValues = new ArrayList<Double>();
		final List<WeightedReturn> historicWeightedReturns = new ArrayList<WeightedReturn>();
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (final Position position : positions) {
				final double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				final double historicReturn = historicValues.get(i - 1) / historicValues.get(i) - 1.0;
				final double weight = (1.0 - DAMPING_FACTOR) * Math.pow(DAMPING_FACTOR, i - 1) / (1.0 - Math.pow(DAMPING_FACTOR, priceNum - 1));
				historicWeightedReturns.add(new WeightedReturn(weight, historicReturn));
				mean += weight * historicReturn;
			}
		}

		Collections.sort(historicWeightedReturns, new WeightedReturnComparator());

		final double percentile = 0.99;
		// Best initial guess
		WeightedReturn previousWeightedReturn = historicWeightedReturns.get(0);
		double previousCummulativeWeight = 0.0;
		double var = 0.0;
		for (int i = 0; i < priceNum - 1; ++i) {
			final WeightedReturn weightedReturn = historicWeightedReturns.get(i);
			final double cummulativeWeight = previousCummulativeWeight + weightedReturn.getWeight();
			if (cummulativeWeight >= 1.0 - percentile) {
				final double meanAdjustedPreviousReturn = previousWeightedReturn.getReturn() - mean;
				final double meanAdjustedReturn = weightedReturn.getReturn() - mean;
				var = -(meanAdjustedPreviousReturn * (cummulativeWeight - (1.0 - percentile)) + meanAdjustedReturn
						* (1.0 - percentile - previousCummulativeWeight))
						/ weightedReturn.getWeight();
				break;
			}
			previousWeightedReturn = weightedReturn;
			previousCummulativeWeight = cummulativeWeight;
		}

		LOGGER.debug("VaR99: {}", var);

		return var;
	}

	private class WeightedReturn {

		private final double weight;
		private final double r3turn;

		public WeightedReturn(final double weight, final double r3turn) {
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
		public int compare(final WeightedReturn firstWeightedReturn, final WeightedReturn secondWeightedReturn) {
			return (int) Math.signum(firstWeightedReturn.getReturn() - secondWeightedReturn.getReturn());
		}

	}
}
