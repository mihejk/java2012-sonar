package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;

public interface StatisticsService {

	double portfolioValue(Portfolio portfolio);

	double exactVAR95(Portfolio portfolio);

	double exactVAR99(Portfolio portfolio);

	double historicVAR95(Portfolio portfolio);

	double historicVAR99(Portfolio portfolio);

	double hybridVAR95(Portfolio portfolio);

	double hybridVAR99(Portfolio portfolio);

}
