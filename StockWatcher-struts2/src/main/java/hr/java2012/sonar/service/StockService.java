package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Stock;

import java.util.List;

public interface StockService extends AbstractEntityService<Stock> {

	Stock findByTicker(String ticker);

	List<Stock> findByNotInPortfolio(Portfolio portfolio);

}
