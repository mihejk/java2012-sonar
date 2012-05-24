package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;

public interface StatisticsService {

	double exactVar95(Portfolio portfolio);

	double exactVar99(Portfolio portfolio);

}
