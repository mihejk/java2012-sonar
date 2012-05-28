package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;

public interface StatisticsService {

	double portfolioValue(Portfolio portfolio);

	double exactVar95(Portfolio portfolio);

	double exactVar99(Portfolio portfolio);

	double historicVar95(Portfolio portfolio);

	double historicVar99(Portfolio portfolio);

	double hybridVar95(Portfolio portfolio);

	double hybridVar99(Portfolio portfolio);

}
