package hr.java2012.sonar.service.var;

import static ch.lambdaj.Lambda.min;
import static ch.lambdaj.Lambda.on;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractHybridVARService implements ValueAtRiskService {

	private static final double DAMPING_FACTOR = 0.93;
	private static final WeightedReturnComparator WEIGHTED_RETURN_COMPARATOR = new WeightedReturnComparator();

	private final double percentile;

	public AbstractHybridVARService(final double percentile) {
		this.percentile = percentile;
	}

	@Override
	public double calculate(final Map<Position, List<Price>> priceMap) {
		final int priceNum = min(priceMap.values(), on(List.class).size());
		final List<WeightedReturn> returns = new ArrayList<WeightedReturn>();
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
				final double weight = (1.0 - DAMPING_FACTOR) * Math.pow(DAMPING_FACTOR, i - 1) / (1.0 - Math.pow(DAMPING_FACTOR, priceNum - 1));
				returns.add(new WeightedReturn(weight, historicReturn));
				mean += weight * historicReturn;
			}
			previousValue = value;
		}

		Collections.sort(returns, WEIGHTED_RETURN_COMPARATOR);

		// Best initial guess
		WeightedReturn previousWeightedReturn = returns.get(0);
		double previousCummulativeWeight = 0.0;
		double var = 0.0;
		for (int i = 0; i < priceNum - 1; ++i) {
			final WeightedReturn weightedReturn = returns.get(i);
			final double cummulativeWeight = previousCummulativeWeight + weightedReturn.getWeight();
			if (cummulativeWeight >= 1.0 - percentile) {
				final double meanAdjustedPreviousReturn = previousWeightedReturn.getReturn() - mean;
				final double meanAdjustedReturn = weightedReturn.getReturn() - mean;
				var = -(meanAdjustedPreviousReturn * (cummulativeWeight - (1.0 - percentile)) + meanAdjustedReturn
						* (1.0 - percentile - previousCummulativeWeight))
						/ weightedReturn.getWeight();
				break;
			}
			previousWeightedReturn = weightedReturn;
			previousCummulativeWeight = cummulativeWeight;
		}

		return var;

	}

	private static final class WeightedReturn {

		private final double weight;
		private final double r3turn;

		public WeightedReturn(final double weight, final double r3turn) {
			this.weight = weight;
			this.r3turn = r3turn;
		}

		public double getWeight() {
			return weight;
		}

		public double getReturn() {
			return r3turn;
		}

	}

	private static final class WeightedReturnComparator implements Comparator<WeightedReturn>, Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public int compare(final WeightedReturn firstWeightedReturn, final WeightedReturn secondWeightedReturn) {
			return (int) Math.signum(firstWeightedReturn.getReturn() - secondWeightedReturn.getReturn());
		}

	}

}
