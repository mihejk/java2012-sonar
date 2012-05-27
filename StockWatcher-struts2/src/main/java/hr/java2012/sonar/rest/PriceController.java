package hr.java2012.sonar.rest;

import java.util.List;

import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.PriceService;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

@Results({ 
	@Result(name = Action.SUCCESS, type = "redirectAction", params = { "actionName", "stock!show", "entityId", "${stockId}"}),
	@Result(name = "by-stock", type = "tiles", location = "price-by-stock")
})
public class PriceController implements ModelDriven<List<Price>> {
	
	@Autowired
	private PriceService priceService;
	
	private List<Price> priceList;
	
	private Stock stock;
	
	private Long stockId;
	
	private Integer n;
	
	/** Handles GET price!byStock?stock={stockId}&n={n} */
	public String byStock() {
		priceList = priceService.findLastPrices(stock, n);
		return "by-stock";
	}
	
	public String generate() {
		stockId = stock.getId();
		if (n == null) {
			priceService.generatePrice(stock);
		} else {
			priceService.generatePrices(stock, n);
		}
		return Action.SUCCESS;
	}
	
	@Override
	public List<Price> getModel() {
		return priceList;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public Long getStockId() {
		return stockId;
	}

}
