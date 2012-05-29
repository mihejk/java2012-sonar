package hr.java2012.sonar.service.var;

import org.springframework.stereotype.Service;

@Service
public class HistoricVAR95Service extends AbstractHistoricVARService {

	private static final double VAR95_PERCENTILE = 0.95;

	public HistoricVAR95Service() {
		super(VAR95_PERCENTILE);
	}

}
