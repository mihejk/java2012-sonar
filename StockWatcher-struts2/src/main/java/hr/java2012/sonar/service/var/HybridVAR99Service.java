package hr.java2012.sonar.service.var;

import org.springframework.stereotype.Service;

@Service
public class HybridVAR99Service extends AbstractHybridVARService {

	private static final double VAR99_PERCENTILE = 0.99;

	public HybridVAR99Service() {
		super(VAR99_PERCENTILE);
	}

}
