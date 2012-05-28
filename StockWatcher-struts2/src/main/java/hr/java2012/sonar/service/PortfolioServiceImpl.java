package hr.java2012.sonar.service;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.repository.PortfolioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioRepository repository;

	@Override
	public Portfolio findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Portfolio> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Portfolio portfolio) {
		repository.delete(portfolio);
	}

	@Override
	public Portfolio save(Portfolio portfolio) {
		return repository.save(portfolio);
	}

	@Override
	public Portfolio findOne(Long entityId) {
		return repository.findOne(entityId);
	}

}
