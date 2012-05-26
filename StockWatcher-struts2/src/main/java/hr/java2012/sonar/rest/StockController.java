package hr.java2012.sonar.rest;

import java.util.List;

import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.StockService;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

@Results({
		@Result(name = Action.SUCCESS, type = "redirectAction", params = { "actionName", "stock" }),
		@Result(name = "index", type = "tiles", location = "stock-index"),
		@Result(name = "show", type = "tiles", location = "stock-show"),
		@Result(name = "new", type = "tiles", location = "stock-new"),
		@Result(name = "edit", type = "tiles", location = "stock-edit")
})
public class StockController implements ModelDriven<Object> {

	@Autowired
	private StockService stockService;

	private Stock stock = new Stock();
	
	private List<Stock> stockList;
	
	/** Handles GET /stocks requests */
	public String index() {
		stockList = stockService.findAll();
		return "index";
	}

	/** Handles GET /stock/{id} requests */
	public String show() {
		return "show";
	}

	/** Handles GET /stock/{id}/edit requests */
	public String edit() {
		return "edit";
	}

	/** Handles GET /stock/new requests */
	public String editNew() {
		return "new";
	}

	/** Handles DELETE /stock/{id} requests */
	public String destroy() {
		stockService.delete(stock);
		return Action.SUCCESS;
	}

	/** Handles POST /stock requests */
	public String create() {
		stock = stockService.save(stock);
		return Action.SUCCESS;
	}

	/** Handles PUT /stock/{id} requests */
	public String update() {
		stock = stockService.save(stock);
		return Action.SUCCESS;
	}

	@Override
	public Object getModel() {
		return stockList == null ? stock : stockList;
	}

	public void setEntityId(final Long entityId) {
		if (entityId != null) {
			this.stock = stockService.findOne(entityId);
		}
	}

}
