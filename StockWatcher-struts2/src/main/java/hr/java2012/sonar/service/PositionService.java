package hr.java2012.sonar.service;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;

public interface PositionService {
	
	List<Position> findByPortfolio(Portfolio portfolio);

	void delete(Position position);

	Position findOne(Long entityId);

	Position save(Position position);

}
