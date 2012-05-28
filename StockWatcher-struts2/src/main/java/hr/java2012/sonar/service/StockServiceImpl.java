package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.repository.StockRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository repository;

	@Override
	public Stock findByTicker(String ticker) {
		return repository.findByTicker(ticker);
	}

	@Override
	public List<Stock> findByNotInPortfolio(Portfolio portfolio) {
		return repository.findByNotInPortfolio(portfolio);
	}

	@Override
	public List<Stock> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Stock stock) {
		repository.delete(stock);
	}

	@Override
	public Stock save(Stock stock) {
		return repository.save(stock);
	}

	@Override
	public Stock findOne(Long entityId) {
		return repository.findOne(entityId);
	}

}
