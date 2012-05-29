package hr.java2012.sonar.service.var;

import org.springframework.stereotype.Service;

@Service
public class HistoricVAR99Service extends AbstractHistoricVARService {

	private static final double VAR99_PERCENTILE = 0.99;

	public HistoricVAR99Service() {
		super(VAR99_PERCENTILE);
	}

}
