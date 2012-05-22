package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.StockService;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({
		@Result(name = "index", type = "tiles", location = "stocks-index")
})
public class StocksController implements ModelDriven<List<Stock>> {

	@Autowired
	private StockService stockService;

	private List<Stock> model;

	public String index() {
		model = stockService.findAll();
		return "index";
	}

	@Override
	public List<Stock> getModel() {
		return model;
	}

}
