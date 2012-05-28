package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.model.Stock;

import java.util.List;

public interface PriceService {

	Price findLastPrice(Stock stock);

	List<Price> findLastPrices(Stock stock, int n);

	long countPrices(Stock stock);

	Price generatePrice(Stock stock);

	List<Price> generatePrices(Stock stock, int n);

}
