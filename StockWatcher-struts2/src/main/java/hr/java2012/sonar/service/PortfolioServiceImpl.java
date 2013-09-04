package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.repository.PortfolioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PortfolioServiceImpl extends AbstractEntityServiceImpl<Portfolio, PortfolioRepository> implements PortfolioService {

	@Autowired
	public PortfolioServiceImpl(final PortfolioRepository repository) {
		super(repository);
	}

	@Override
	public Portfolio findByName(final String name) {
		return getRepository().findByName(name);
	}

}
