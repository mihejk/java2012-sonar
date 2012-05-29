package hr.java2012.sonar.service.var;

import static ch.lambdaj.Lambda.min;
import static ch.lambdaj.Lambda.on;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractHistoricVARService implements ValueAtRiskService {

	private final double percentile;

	public AbstractHistoricVARService(final double percentile) {
		this.percentile = percentile;
	}

	@Override
	public double calculate(final Map<Position, List<Price>> priceMap) {
		final int priceNum = min(priceMap.values(), on(List.class).size());
		final List<Double> returns = new ArrayList<Double>();
		double previousValue = 0.0;
		double mean = 0.0;
		for (int i = 0; i < priceNum; ++i) {
			double value = 0.0;
			for (final Entry<Position, List<Price>> entry : priceMap.entrySet()) {
				final Position position = entry.getKey();
				final List<Price> prices = entry.getValue();
				final double priceValue = prices.get(i).getValue();
				value += priceValue * position.getQuantity();
			}
			if (i > 0) {
				// Prices are sorted descending with respect to time (zeroth
				// price is the latest)
				final double historicReturn = previousValue / value - 1.0;
				returns.add(historicReturn);
				mean += historicReturn;
			}
			previousValue = value;
		}
		mean /= (priceNum - 1);

		Collections.sort(returns);

		// A dummy sentinel
		returns.add(Double.NaN);
		final double position = (1.0 - percentile) * (priceNum - 2);
		final int index = (int) Math.floor(position);
		final double meanAdjustedReturn = returns.get(index) - mean;
		final double meanAdjustedReturn1 = returns.get(index + 1) - mean;

		return -((index + 1 - position) * meanAdjustedReturn + (position - index) * meanAdjustedReturn1);
	}

}
