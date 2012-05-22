package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.service.PortfolioService;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({
		@Result(name = "success", type = "redirectAction", params = { "actionName", "portfolios" }),
		@Result(name = "show", type = "tiles", location = "portfolio-show"),
		@Result(name = "new", type = "tiles", location = "portfolio-new"),
		@Result(name = "edit", type = "tiles", location = "portfolio-edit")
})
public class PortfolioController implements ModelDriven<Portfolio> {

	@Autowired
	private PortfolioService portfolioService;

	private Portfolio model = new Portfolio();

	/** Handles GET /portfolio/{id} requests */
	public String show() {
		return "show";
	}

	/** Handles GET /portfolio/{id}/edit requests */
	public String edit() {
		return "edit";
	}

	/** Handles GET /portfolio/new requests */
	public String editNew() {
		return "new";
	}

	/** Handles DELETE /portfolio/{id} requests */
	public String destroy() {
		portfolioService.delete(model);
		return "success";
	}

	/** Handles POST /portfolio requests */
	public String create() {
		model = portfolioService.save(model);
		return "success";
	}

	/** Handles PUT /portfolio/{id} requests */
	public String update() {
		model = portfolioService.save(model);
		return "success";
	}

	@Override
	public Portfolio getModel() {
		return model;
	}

	public void setModel(final Portfolio model) {
		this.model = model;
	}

	public void setEntityId(final Long entityId) {
		if (entityId != null) {
			this.model = portfolioService.findOne(entityId);
		}
	}

}
