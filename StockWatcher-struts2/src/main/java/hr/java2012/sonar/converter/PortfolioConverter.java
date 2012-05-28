package hr.java2012.sonar.converter;

import java.util.Map;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.service.PortfolioService;

import org.apache.struts2.util.StrutsTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class PortfolioConverter extends StrutsTypeConverter {
	
	@Autowired
	private PortfolioService portfolioService;

	@Override
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Long id = Long.parseLong(values[0]);
		return portfolioService.findOne(id);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object o) {
		Portfolio portfolio = (Portfolio) o;
		return portfolio.getId().toString();
	}

}
