package hr.java2012.sonar.service.var;

import org.springframework.stereotype.Service;

@Service
public class ExactVAR95Service extends AbstractExactVARService {

	private static final double VAR95_STANDARD = 1.64485362695;

	public ExactVAR95Service() {
		super(VAR95_STANDARD);
	}

}
