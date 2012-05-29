package hr.java2012.sonar.service.var;

import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractExactVARService implements ValueAtRiskService {

	private final double standardVar;

	public AbstractExactVARService(final double standardVar) {
		this.standardVar = standardVar;
	}

	@Override
	public double calculate(final Map<Position, List<Price>> priceMap) {
		double totalValue = 0.0;
		for (final Entry<Position, List<Price>> entry : priceMap.entrySet()) {
			final Position position = entry.getKey();
			final Price price = entry.getValue().get(0);
			totalValue += position.getQuantity() * price.getValue();
		}
		double variance = 0.0;
		for (final Entry<Position, List<Price>> entry : priceMap.entrySet()) {
			final Position position = entry.getKey();
			final Price price = entry.getValue().get(0);
			final double value = position.getQuantity() * price.getValue();
			final double weight = value / totalValue;
			final double stockStdDev = position.getStock().getStdDev();
			variance += weight * weight * stockStdDev * stockStdDev;
		}
		return standardVar * Math.sqrt(variance);
	}

}
