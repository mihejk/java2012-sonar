package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.repository.StockRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StockServiceImpl extends AbstractEntityServiceImpl<Stock, StockRepository> implements StockService {

	@Autowired
	public StockServiceImpl(final StockRepository repository) {
		super(repository);
	}

	@Override
	public Stock findByTicker(final String ticker) {
		return getRepository().findByTicker(ticker);
	}

	@Override
	public List<Stock> findByNotInPortfolio(final Portfolio portfolio) {
		return getRepository().findByNotInPortfolio(portfolio);
	}

}
