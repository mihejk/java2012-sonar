package hr.java2012.sonar.converter;

import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.StockService;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class StockConverter extends StrutsTypeConverter {

	@Autowired
	private StockService stockService;
	
	@Override
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Long id = Long.parseLong(values[0]);
		return stockService.findOne(id);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object o) {
		Stock stock = (Stock) o;
		return stock.getId().toString();
	}
	
}
