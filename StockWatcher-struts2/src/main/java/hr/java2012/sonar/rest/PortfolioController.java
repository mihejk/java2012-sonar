package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.service.PortfolioService;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

public class PortfolioController implements ModelDriven<Portfolio> {

	@Autowired
	private PortfolioService portfolioService;

	private Long id;

	private Portfolio model;

	public String show() {
		model = portfolioService.findOne(id);
		return "show";
	}

	@Override
	public Portfolio getModel() {
		return model;
	}

	public void setId(final Long id) {
		this.id = id;
	}

}
