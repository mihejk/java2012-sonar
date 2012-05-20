package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.service.PortfolioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

public class PortfoliosController implements ModelDriven<List<Portfolio>> {

	@Autowired
	private PortfolioService portfolioService;

	private List<Portfolio> model;

	public String index() {
		model = portfolioService.findAll();
		return "index";
	}

	@Override
	public List<Portfolio> getModel() {
		return model;
	}

}
