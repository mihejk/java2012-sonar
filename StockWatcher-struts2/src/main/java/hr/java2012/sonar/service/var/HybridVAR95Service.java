package hr.java2012.sonar.service.var;

import org.springframework.stereotype.Service;

@Service
public class HybridVAR95Service extends AbstractHybridVARService {

	private static final double VAR95_PERCENTILE = 0.95;

	public HybridVAR95Service() {
		super(VAR95_PERCENTILE);
	}

}
