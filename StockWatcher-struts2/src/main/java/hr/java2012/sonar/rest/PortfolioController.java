package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.service.PortfolioService;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({
	@Result(name = "success", type="redirectAction", params = {"actionName" , "portfolios"}),
	@Result(name = "show", type = "tiles", location = "portfolio-show"),
	@Result(name = "new", type = "tiles", location = "portfolio-new"),
	@Result(name = "edit", type = "tiles", location = "portfolio-edit")
})
public class PortfolioController implements ModelDriven<Portfolio> {

	@Autowired
	private PortfolioService portfolioService;
	
	private Portfolio model = new Portfolio();

	/** GET /portfolio/1 */
	public String show() {
		return "show";
	}
	
	/** GET /portfolio/1/edit */
	public String edit() {
		return "edit";
	}
	
	/** GET /portfolio/new */
	public String editNew() {
		return "new";
	}
	
	/** DELETE /portfolio/1 */
	public String delete() {
		portfolioService.delete(model);
		return "success";
	}
	
	/** POST /portfolio */
	public String create() {
		model = portfolioService.save(model);
		return "success";
	}
	
	/** PUT /portfolio/1 */
	public String update() {
		model = portfolioService.save(model);
		return "success";
	}

	@Override
	public Portfolio getModel() {
		return model;
	}

	public void setModelId(final Long modelId) {
		if (modelId != null) {
			this.model = portfolioService.findOne(modelId);
		}
	}

}
