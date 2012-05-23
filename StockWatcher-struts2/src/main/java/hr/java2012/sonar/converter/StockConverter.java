package hr.java2012.sonar.converter;

import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;

public class StockConverter extends AbstractEntityConverter<Stock> {

	@Autowired
	public StockConverter(final StockService service) {
		super(Stock.class, service);
	}

}
