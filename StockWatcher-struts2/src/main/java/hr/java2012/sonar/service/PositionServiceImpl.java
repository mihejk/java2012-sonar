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
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	private PositionRepository repository;

	@Override
	public List<Position> findByPortfolio(Portfolio portfolio) {
		return repository.findByPortfolio(portfolio);
	}

	@Override
	public void delete(Position position) {
		repository.delete(position);
	}

	@Override
	public Position findOne(Long entityId) {
		return repository.findOne(entityId);
	}

	@Override
	public Position save(Position position) {
		return repository.save(position);
	}

}
