package hr.java2012.sonar.service.var;

import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;

import java.util.List;
import java.util.Map;

public interface ValueAtRiskService {

	double calculate(Map<Position, List<Price>> priceMap);

}
