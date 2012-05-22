package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl extends AbstractEntityServiceImpl<Position> implements PositionService {

	@Autowired
	public PositionServiceImpl(final PositionRepository repository) {
		super(repository);
	}

}
