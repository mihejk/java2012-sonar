package hr.java2012.sonar.service.var;

import org.springframework.stereotype.Service;

@Service
public class ExactVAR99Service extends AbstractExactVARService {

	private static final double VAR99_STANDARD = 2.32634787404;

	public ExactVAR99Service() {
		super(VAR99_STANDARD);
	}

}
