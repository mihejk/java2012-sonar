package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.service.PositionService;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

@Results({ @Result(name = Action.SUCCESS, type = "redirectAction", params = { "actionName", "portfolio!show", "entityId", "${portfolioId}" }) })
public class PositionController implements ModelDriven<Position> {

	@Autowired
	private PositionService positionService;

	private Position model = new Position();

	private Long portfolioId;

	/** Handles DELETE /position/{id} requests */
	public String destroy() {
		portfolioId = model.getPortfolio().getId();
		positionService.delete(model);
		return Action.SUCCESS;
	}

	/** Handles POST /position requests */
	public String create() {
		portfolioId = model.getPortfolio().getId();
		model = positionService.save(model);
		return Action.SUCCESS;
	}

	@Override
	public Position getModel() {
		return model;
	}

	public void setModel(Position model) {
		this.model = model;
	}

	public void setEntityId(Long entityId) {
		if (entityId != null) {
			this.model = positionService.findOne(entityId);
		}
	}

	public Long getPortfolioId() {
		return portfolioId;
	}

}
