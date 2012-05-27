package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;

import java.util.ArrayList;
import java.util.Collections;
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
		double mean = 0.0;
		double stdDev = 0.0;
		for (int i = 0; i < positions.size(); ++i) {
			final Position position = positions.get(i);
			final Price price = prices.get(i);
			final double value = position.getQuantity() * price.getValue();
			final double weight = value / totalValue;
			mean += weight * position.getStock().getMean();
			stdDev += weight * weight * position.getStock().getStdDev() * position.getStock().getStdDev();
			LOGGER.debug("\tPosition: {} Weight: {} Quantity: {} Price: {} Value: {}",
					new Object[] { position.getStock().getTicker(), weight, position.getQuantity(), price.getValue(), value });
		}
		stdDev = Math.sqrt(stdDev);

		final double var = VAR95_STANDARD * stdDev + mean;
		LOGGER.debug("Total: Value: {} Mean: {} StdDev: {} VaR95: {}",
				new Object[] { totalValue, mean, stdDev, var });

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
		double mean = 0.0;
		double stdDev = 0.0;
		for (int i = 0; i < positions.size(); ++i) {
			final Position position = positions.get(i);
			final Price price = prices.get(i);
			final double value = position.getQuantity() * price.getValue();
			final double weight = value / totalValue;
			mean += weight * position.getStock().getMean();
			stdDev += weight * weight * position.getStock().getStdDev() * position.getStock().getStdDev();
			LOGGER.debug("\tPosition: {} Weight: {} Quantity: {} Price: {} Value: {}",
					new Object[] { position.getStock().getTicker(), weight, position.getQuantity(), price.getValue(), value });
		}
		stdDev = Math.sqrt(stdDev);

		final double var = VAR99_STANDARD * stdDev + mean;
		LOGGER.debug("Total: Value: {} Mean: {} StdDev: {} VaR99: {}",
				new Object[] { totalValue, mean, stdDev, var });

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
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (final Position position : positions) {
				final double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				final double historicReturn = historicValues.get(i) / historicValues.get(i - 1) - 1.0;
				historicReturns.add(historicReturn);
			}
		}

		Collections.sort(historicReturns);

		// A dummy sentinel
		historicReturns.add(Double.NaN);
		final double percentile = 0.95;
		final double position = (1.0 - percentile) * (priceNum - 2);
		final int index = (int) Math.floor(position);

		final double var = -((index + 1 - position) * historicReturns.get(index) + (position - index) * historicReturns.get(index + 1));
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
		for (int i = 0; i < priceNum; ++i) {
			double historicValue = 0.0;
			for (final Position position : positions) {
				final double historicPrice = historicPrices.get(position).get(i).getValue();
				historicValue += historicPrice * position.getQuantity();
			}
			historicValues.add(historicValue);

			if (i > 0) {
				final double historicReturn = historicValues.get(i) / historicValues.get(i - 1) - 1.0;
				historicReturns.add(historicReturn);
			}
		}

		Collections.sort(historicReturns);

		// A dummy sentinel
		historicReturns.add(Double.NaN);
		final double percentile = 0.99;
		final double position = (1.0 - percentile) * (priceNum - 2);
		final int index = (int) Math.floor(position);

		final double var = -((index + 1 - position) * historicReturns.get(index) + (position - index) * historicReturns.get(index + 1));
		LOGGER.debug("VaR99: {}", var);

		return var;
	}

	@Override
	public double hybridVar95(final Portfolio portfolio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double hybridVar99(final Portfolio portfolio) {
		// TODO Auto-generated method stub
		return 0;
	}

}
