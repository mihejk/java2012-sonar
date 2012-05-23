package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.StockService;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

@Results({
		@Result(name = Action.SUCCESS, type = "redirectAction", params = { "actionName", "stocks" }),
		@Result(name = "show", type = "tiles", location = "stock-show"),
		@Result(name = "new", type = "tiles", location = "stock-new"),
		@Result(name = "edit", type = "tiles", location = "stock-edit")
})
public class StockController implements ModelDriven<Stock> {

	@Autowired
	private StockService stockService;

	private Stock model = new Stock();

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
		stockService.delete(model);
		return Action.SUCCESS;
	}

	/** Handles POST /stock requests */
	public String create() {
		model = stockService.save(model);
		return Action.SUCCESS;
	}

	/** Handles PUT /stock/{id} requests */
	public String update() {
		model = stockService.save(model);
		return Action.SUCCESS;
	}

	@Override
	public Stock getModel() {
		return model;
	}

	public void setModel(final Stock model) {
		this.model = model;
	}

	public void setEntityId(final Long entityId) {
		if (entityId != null) {
			this.model = stockService.findOne(entityId);
		}
	}

}
