package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.repository.PortfolioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioRepository repository;

	@Override
	public Portfolio save(final Portfolio entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(final Portfolio entity) {
		repository.delete(entity);
	}

	@Override
	public List<Portfolio> findAll() {
		return repository.findAll();
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public Portfolio findOne(final Long id) {
		return repository.findOne(id);
	}

}
