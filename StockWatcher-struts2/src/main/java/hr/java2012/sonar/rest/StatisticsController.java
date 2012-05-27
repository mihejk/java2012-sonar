package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.service.PortfolioService;
import hr.java2012.sonar.service.StatisticsService;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

@Results({ 
	@Result(name = Action.SUCCESS, type = "redirectAction", params = { "actionName", "statistics" }),
	@Result(name = "index", type = "tiles", location = "statistics-index"),
	@Result(name = "result", type = "tiles", location = "statistics-result")
})
public class StatisticsController implements ModelDriven<Double> {

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private StatisticsService statisticsService;

	private Portfolio portfolio;
	
	private String statisticsMethod;

	private List<Portfolio> portfolioList;

	private Double result;

	/** Handles GET /statistics requests */
	public String index() {
		portfolioList = portfolioService.findAll();
		return "index";
	}

	/** Handles GET /statistics!exactVar95?portfolio={id} requests */
	public String exactVar95() {
		statisticsMethod = "exact_var_95";
		result = statisticsService.exactVar95(portfolio);
		return "result";
	}
	
	/** Handles GET /statistics!exactVar99?portfolio={id} requests */
	public String exactVar99() {
		statisticsMethod = "exact_var_99";
		result = statisticsService.exactVar99(portfolio);
		return "result";
	}
	
	
	/** Handles GET /statistics!historicVar95?portfolio={id} requests */
	public String historicVar95() {
		statisticsMethod = "historic_var_95";
		result = statisticsService.historicVar95(portfolio);
		return "result";
	}

	/** Handles GET /statistics!historicVar99?portfolio={id} requests */
	public String historicVar99() {
		statisticsMethod = "historic_var_99";
		result = statisticsService.historicVar99(portfolio);
		return "result";
	}
	
	/** Handles GET /statistics!hybridVar95?portfolio={id} requests */
	public String hybridVar95() {
		statisticsMethod = "hybrid_var_95";
		result = statisticsService.hybridVar95(portfolio);
		return "result";
	}
	
	/** Handles GET /statistics!hybridVar99?portfolio={id} requests */
	public String hybridVar99() {
		statisticsMethod = "hybrid_var_99";
		result = statisticsService.hybridVar99(portfolio);
		return "result";
	}

	@Override
	public Double getModel() {
		return result;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public String getStatisticsMethod() {
		return statisticsMethod;
	}

	public List<Portfolio> getPortfolioList() {
		return portfolioList;
	}

}
