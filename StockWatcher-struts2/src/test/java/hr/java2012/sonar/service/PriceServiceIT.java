package hr.java2012.sonar.service;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.test.AbstractTransactionalIT;

import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PriceServiceIT extends AbstractTransactionalIT {

	private static Logger LOGGER = LoggerFactory.getLogger(PriceServiceIT.class);

	@Autowired
	private PriceService priceService;

	@Autowired
	private StockService stockService;

	@Test
	public void testFindLastPrices() {
		Stock stock = stockService.findByTicker("FB");
		List<Price> generatedPrices = priceService.generatePrices(stock, 100).subList(50, 100);
		List<Price> fetchedPrices = priceService.findLastPrices(stock, 50);

		Collections.reverse(generatedPrices);
		List<Long> generatedPriceIds = extract(generatedPrices, on(Price.class).getId());
		List<Long> fetchedPriceIds = extract(fetchedPrices, on(Price.class).getId());

		assertThat(fetchedPriceIds, hasSize(generatedPriceIds.size()));
		assertThat(fetchedPriceIds, contains(generatedPriceIds.toArray()));
	}

	@Test
	@Ignore
	public void testGeneratePrices() {
		Stock stock = stockService.findByTicker("NFLX");
		List<Price> generatedPrices = priceService.generatePrices(stock, 50);

		for (Price price : generatedPrices) {
			LOGGER.info("insert into Price (stock_id, value) values ({}, {});", price.getStock().getId(), price.getValue());
		}
	}

}
