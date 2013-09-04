package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.repository.PositionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl extends AbstractEntityServiceImpl<Position, PositionRepository> implements PositionService {

	@Autowired
	public PositionServiceImpl(final PositionRepository repository) {
		super(repository);
	}

	@Override
	public List<Position> findByPortfolio(final Portfolio portfolio) {
		return getRepository().findByPortfolio(portfolio);
	}

}
