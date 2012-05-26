package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceImpl.class);

	private static final double VAR99_STANDARD = 2.32634787404;
	private static final double VAR95_STANDARD = 1.64485362695;

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

		final double var95 = VAR95_STANDARD * stdDev + mean;
		LOGGER.debug("Total: Value: {} Mean: {} StdDev: {} VaR95: {}",
				new Object[] { totalValue, mean, stdDev, var95 });

		return var95;
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

		final double var99 = VAR99_STANDARD * stdDev + mean;
		LOGGER.debug("Total: Value: {} Mean: {} StdDev: {} VaR99: {}",
				new Object[] { totalValue, mean, stdDev, var99 });

		return var99;
	}

}
