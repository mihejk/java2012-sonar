package hr.java2012.sonar.service;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PositionServiceImpl extends AbstractEntityServiceImpl<Position> implements PositionService {
	
	private final PositionRepository repository;

	@Autowired
	public PositionServiceImpl(final PositionRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public List<Position> findByPortfolio(final Portfolio portfolio) {
		return repository.findByPortfolio(portfolio);
	}

}
