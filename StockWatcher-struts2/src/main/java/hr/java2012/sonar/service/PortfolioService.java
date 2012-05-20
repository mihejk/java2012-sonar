package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;

import java.util.List;

public interface PortfolioService {
	
	Portfolio save(Portfolio entity);
	
	void delete(Portfolio entity);
	
	List<Portfolio> findAll();
	
	long count();
	
	Portfolio findOne(Long id);

}
