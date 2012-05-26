package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;

public interface PortfolioService extends AbstractEntityService<Portfolio> {

	Portfolio findByName(String name);

}
