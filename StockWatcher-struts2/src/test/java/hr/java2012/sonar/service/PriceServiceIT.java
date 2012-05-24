package hr.java2012.sonar.service;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.service.PriceService;
import hr.java2012.sonar.service.StockService;
import hr.java2012.sonar.test.AbstractTransactionalIT;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PriceServiceIT extends AbstractTransactionalIT {

	@Autowired
	private PriceService priceService;

	@Autowired
	private StockService stockService;

	@Test
	public void testFindLastPrices() {
		final Stock stock = stockService.findByTicker("FB");
		final List<Price> generatedPrices = priceService.generatePrices(stock, 100).subList(50, 100);
		final List<Price> fetchedPrices = priceService.findLastPrices(stock, 50);

		Collections.reverse(generatedPrices);
		final List<Long> generatedPriceIds = extract(generatedPrices, on(Price.class).getId());
		final List<Long> fetchedPriceIds = extract(fetchedPrices, on(Price.class).getId());

		assertThat(fetchedPriceIds, hasSize(generatedPriceIds.size()));
		assertThat(fetchedPriceIds, contains(generatedPriceIds.toArray()));
	}

}
