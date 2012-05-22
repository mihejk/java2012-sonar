package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends AbstractEntityServiceImpl<Stock> implements StockService {

	@Autowired
	public StockServiceImpl(final StockRepository repository) {
		super(repository);
	}

}
