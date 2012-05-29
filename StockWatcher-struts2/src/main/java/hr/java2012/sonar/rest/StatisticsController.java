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
	
	private String analysisMethod;

	private List<Portfolio> portfolioList;

	private Double result;

	/** Handles GET /statistics requests */
	public String index() {
		portfolioList = portfolioService.findAll();
		return "index";
	}

	/** Handles GET /statistics!exactVar95?portfolio={id} requests */
	public String exactVar95() {
		analysisMethod = "exactVar95";
		result = statisticsService.exactVAR95(portfolio);
		return "result";
	}
	
	/** Handles GET /statistics!exactVar99?portfolio={id} requests */
	public String exactVar99() {
		analysisMethod = "exactVar99";
		result = statisticsService.exactVAR99(portfolio);
		return "result";
	}
	
	
	/** Handles GET /statistics!historicVar95?portfolio={id} requests */
	public String historicVar95() {
		analysisMethod = "historicVar95";
		result = statisticsService.historicVAR95(portfolio);
		return "result";
	}

	/** Handles GET /statistics!historicVar99?portfolio={id} requests */
	public String historicVar99() {
		analysisMethod = "historicVar99";
		result = statisticsService.historicVAR99(portfolio);
		return "result";
	}
	
	/** Handles GET /statistics!hybridVar95?portfolio={id} requests */
	public String hybridVar95() {
		analysisMethod = "hybridVar95";
		result = statisticsService.hybridVAR95(portfolio);
		return "result";
	}
	
	/** Handles GET /statistics!hybridVar99?portfolio={id} requests */
	public String hybridVar99() {
		analysisMethod = "hybridVar99";
		result = statisticsService.hybridVAR99(portfolio);
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

	public String getAnalysisMethod() {
		return analysisMethod;
	}

	public List<Portfolio> getPortfolioList() {
		return portfolioList;
	}

}
