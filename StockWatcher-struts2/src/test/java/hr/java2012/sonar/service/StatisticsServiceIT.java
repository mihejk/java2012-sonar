package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.test.AbstractTransactionalIT;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StatisticsServiceIT extends AbstractTransactionalIT {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsServiceIT.class);

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private StatisticsService statisticsService;

	@Test
	public void testExactVar95() {
		final Portfolio portfolio1 = portfolioService.findByName("Portfolio1");
		final Portfolio portfolio2 = portfolioService.findByName("Portfolio2");

		LOGGER.info("{} exact VaR95: {}", portfolio1.getName(), statisticsService.exactVar95(portfolio1));
		LOGGER.info("{} exact VaR95: {}", portfolio2.getName(), statisticsService.exactVar95(portfolio2));
	}

	@Test
	public void testExactVar99() {
		final Portfolio portfolio1 = portfolioService.findByName("Portfolio1");
		final Portfolio portfolio2 = portfolioService.findByName("Portfolio2");

		LOGGER.info("{} exact VaR99: {}", portfolio1.getName(), statisticsService.exactVar99(portfolio1));
		LOGGER.info("{} exact VaR99: {}", portfolio2.getName(), statisticsService.exactVar99(portfolio2));
	}

	@Test
	public void testHistoricVar95() {
		final Portfolio portfolio1 = portfolioService.findByName("Portfolio1");
		final Portfolio portfolio2 = portfolioService.findByName("Portfolio2");

		LOGGER.info("{} historic VaR95: {}", portfolio1.getName(), statisticsService.historicVar95(portfolio1));
		LOGGER.info("{} historic VaR95: {}", portfolio2.getName(), statisticsService.historicVar95(portfolio2));
	}

	@Test
	public void testHistoricVar99() {
		final Portfolio portfolio1 = portfolioService.findByName("Portfolio1");
		final Portfolio portfolio2 = portfolioService.findByName("Portfolio2");

		LOGGER.info("{} historic VaR99: {}", portfolio1.getName(), statisticsService.historicVar99(portfolio1));
		LOGGER.info("{} historic VaR99: {}", portfolio2.getName(), statisticsService.historicVar99(portfolio2));
	}

	@Test
	public void testHybridVar95() {
		final Portfolio portfolio1 = portfolioService.findByName("Portfolio1");
		final Portfolio portfolio2 = portfolioService.findByName("Portfolio2");

		LOGGER.info("{} hybrid VaR95: {}", portfolio1.getName(), statisticsService.hybridVar95(portfolio1));
		LOGGER.info("{} hybrid VaR95: {}", portfolio2.getName(), statisticsService.hybridVar95(portfolio2));
	}

	@Test
	public void testHybridVar99() {
		final Portfolio portfolio1 = portfolioService.findByName("Portfolio1");
		final Portfolio portfolio2 = portfolioService.findByName("Portfolio2");

		LOGGER.info("{} hybrid VaR99: {}", portfolio1.getName(), statisticsService.hybridVar99(portfolio1));
		LOGGER.info("{} hybrid VaR99: {}", portfolio2.getName(), statisticsService.hybridVar99(portfolio2));
	}

}
