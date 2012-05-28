package hr.java2012.sonar.service;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;

public interface PortfolioService {

	Portfolio findByName(String name);

	List<Portfolio> findAll();

	void delete(Portfolio portfolio);

	Portfolio save(Portfolio portfolio);

	Portfolio findOne(Long entityId);

}
