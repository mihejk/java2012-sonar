package hr.java2012.sonar.converter;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.service.PortfolioService;

import org.springframework.beans.factory.annotation.Autowired;

public class PortfolioConverter extends AbstractEntityConverter<Portfolio> {

	@Autowired
	public PortfolioConverter(final PortfolioService service) {
		super(Portfolio.class, service);
	}

}
