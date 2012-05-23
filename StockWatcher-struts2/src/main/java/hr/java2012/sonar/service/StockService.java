package hr.java2012.sonar.service;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Stock;

public interface StockService extends AbstractEntityService<Stock> {
	
	List<Stock> findByNotInPortfolio(Portfolio portfolio);
}
