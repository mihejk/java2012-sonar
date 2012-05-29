package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.service.var.ExactVAR95Service;
import hr.java2012.sonar.service.var.ExactVAR99Service;
import hr.java2012.sonar.service.var.HistoricVAR95Service;
import hr.java2012.sonar.service.var.HistoricVAR99Service;
import hr.java2012.sonar.service.var.HybridVAR95Service;
import hr.java2012.sonar.service.var.HybridVAR99Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private PositionService positionService;

	@Autowired
	private PriceService priceService;

	@Autowired
	private ExactVAR95Service exactVAR95Service;

	@Autowired
	private ExactVAR99Service exactVAR99Service;

	@Autowired
	private HistoricVAR95Service historicVAR95Service;

	@Autowired
	private HistoricVAR99Service historicVAR99Service;

	@Autowired
	private HybridVAR95Service hybridVAR95Service;

	@Autowired
	private HybridVAR99Service hybridVAR99Service;

	@Override
	public double portfolioValue(final Portfolio portfolio) {
		final List<Position> positions = positionService.findByPortfolio(portfolio);
		double totalValue = 0.0;
		for (final Position position : positions) {
			final Price price = priceService.findLastPrice(position.getStock());
			totalValue += position.getQuantity() * price.getValue();
		}
		return totalValue;
	}

	@Override
	public double exactVAR95(final Portfolio portfolio) {
		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final Map<Position, List<Price>> priceMap = prepareLastPriceMap(positions);
		return exactVAR95Service.calculate(priceMap);
	}

	@Override
	public double exactVAR99(final Portfolio portfolio) {
		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final Map<Position, List<Price>> priceMap = prepareLastPriceMap(positions);
		return exactVAR99Service.calculate(priceMap);
	}

	@Override
	public double historicVAR95(final Portfolio portfolio) {
		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final Map<Position, List<Price>> priceMap = prepareHistoricPriceMap(positions);
		return historicVAR95Service.calculate(priceMap);
	}

	@Override
	public double historicVAR99(final Portfolio portfolio) {
		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final Map<Position, List<Price>> priceMap = prepareHistoricPriceMap(positions);
		return historicVAR99Service.calculate(priceMap);
	}

	@Override
	public double hybridVAR95(final Portfolio portfolio) {
		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final Map<Position, List<Price>> priceMap = prepareHistoricPriceMap(positions);
		return hybridVAR95Service.calculate(priceMap);
	}

	@Override
	public double hybridVAR99(final Portfolio portfolio) {
		final List<Position> positions = positionService.findByPortfolio(portfolio);
		final Map<Position, List<Price>> priceMap = prepareHistoricPriceMap(positions);
		return hybridVAR99Service.calculate(priceMap);
	}

	private Map<Position, List<Price>> prepareLastPriceMap(final List<Position> positions) {
		return preparePriceMap(positions, 1);
	}

	private Map<Position, List<Price>> prepareHistoricPriceMap(final List<Position> positions) {
		int priceNum = Integer.MAX_VALUE;
		for (final Position position : positions) {
			priceNum = Math.min(priceNum, (int) priceService.countPrices(position.getStock()));
		}
		return preparePriceMap(positions, priceNum);
	}

	private Map<Position, List<Price>> preparePriceMap(final List<Position> positions, final int priceNum) {
		final Map<Position, List<Price>> resultMap = new HashMap<Position, List<Price>>();
		for (final Position position : positions) {
			resultMap.put(position, priceService.findLastPrices(position.getStock(), priceNum));
		}
		return resultMap;
	}

}
