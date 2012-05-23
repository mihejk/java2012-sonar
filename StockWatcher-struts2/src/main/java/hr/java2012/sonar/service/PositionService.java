package hr.java2012.sonar.service;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;

public interface PositionService extends AbstractEntityService<Position> {
	
	List<Position> findByPortfolio(Portfolio portfolio);

}
