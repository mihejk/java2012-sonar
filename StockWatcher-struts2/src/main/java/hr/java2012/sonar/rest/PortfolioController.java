package hr.java2012.sonar.rest;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;
import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.PortfolioService;
import hr.java2012.sonar.service.PositionService;
import hr.java2012.sonar.service.PriceService;
import hr.java2012.sonar.service.StatisticsService;
import hr.java2012.sonar.service.StockService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

@Results({
		@Result(name = Action.SUCCESS, type = "redirectAction", params = { "actionName", "portfolio" }),
		@Result(name = "index", type = "tiles", location = "portfolio-index"),
		@Result(name = "show", type = "tiles", location = "portfolio-show"),
		@Result(name = "new", type = "tiles", location = "portfolio-new"),
		@Result(name = "edit", type = "tiles", location = "portfolio-edit")
})
public class PortfolioController implements ModelDriven<Object> {

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private PositionService positionService;

	@Autowired
	private StockService stockService;

	@Autowired
	private StatisticsService statisticsService;

	@Autowired
	private PriceService priceService;

	private Portfolio portfolio = new Portfolio();

	private List<Portfolio> portfolioList;

	private List<Position> positions;

	private List<Stock> stocks;

	private Map<String, Double> statistics;

	private List<Price> lastPrice;

	/** Handles GET /portfolio requests */
	public String index() {
		portfolioList = portfolioService.findAll();
		return "index";
	}

	/** Handles GET /portfolio/{id} requests */
	public String show() {
		positions = positionService.findByPortfolio(portfolio);
		stocks = stockService.findByNotInPortfolio(portfolio);

		// var
		statistics = new HashMap<String, Double>();
		statistics.put("exactVar95", statisticsService.exactVar95(portfolio));
		statistics.put("exactVar99", statisticsService.exactVar99(portfolio));
		statistics.put("historicVar95", statisticsService.historicVar95(portfolio));
		statistics.put("historicVar99", statisticsService.historicVar99(portfolio));
		statistics.put("hybridVar95", statisticsService.hybridVar95(portfolio));
		statistics.put("hybridVar99", statisticsService.hybridVar99(portfolio));

		// value
		statistics.put("value", statisticsService.portfolioValue(portfolio));

		// prices
		lastPrice = new ArrayList<Price>();
		for (Position position : positions) {
			Price price = priceService.findLastPrice(position.getStock());
			lastPrice.add(price);
		}

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
		portfolioService.delete(portfolio);
		return Action.SUCCESS;
	}

	/** Handles POST /portfolio requests */
	public String create() {
		portfolio = portfolioService.save(portfolio);
		return Action.SUCCESS;
	}

	/** Handles PUT /portfolio/{id} requests */
	public String update() {
		portfolio = portfolioService.save(portfolio);
		return Action.SUCCESS;
	}

	@Override
	public Object getModel() {
		return (portfolioList == null ? portfolio : portfolioList);
	}

	public void setEntityId(Long entityId) {
		if (entityId != null) {
			this.portfolio = portfolioService.findOne(entityId);
		}
	}

	public List<Position> getPositions() {
		return positions;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public Map<String, Double> getStatistics() {
		return statistics;
	}

	public List<Price> getLastPrice() {
		return lastPrice;
	}

}
