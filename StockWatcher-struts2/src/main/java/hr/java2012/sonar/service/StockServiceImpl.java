package hr.java2012.sonar.service;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl extends AbstractEntityServiceImpl<Stock> implements StockService {
	
	private final StockRepository repository;

	@Autowired
	public StockServiceImpl(final StockRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public List<Stock> findByNotInPortfolio(Portfolio portfolio) {
		return repository.findByNotInPortfolio(portfolio);
	}

}
