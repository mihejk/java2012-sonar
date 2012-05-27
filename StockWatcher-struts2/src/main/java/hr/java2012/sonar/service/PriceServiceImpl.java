package hr.java2012.sonar.service;

import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.model.Stock;
import hr.java2012.sonar.repository.PriceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl extends AbstractEntityServiceImpl<Price> implements PriceService {

	private final PriceRepository repository;

	private final Random random = new Random();

	@Autowired
	public PriceServiceImpl(final PriceRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public Price findLastPrice(final Stock stock) {
		return repository.findLastByStock(stock);
	}

	@Override
	public List<Price> findLastPrices(final Stock stock, final int n) {
		final PageRequest pageRequest = new PageRequest(0, n, Sort.Direction.DESC, "id");
		final Page<Price> page = repository.findByStock(stock, pageRequest);
		return page.getContent();
	}

	@Override
	public long countPrices(final Stock stock) {
		return repository.countByStock(stock);
	}

	@Override
	public Price generatePrice(final Stock stock) {
		final Price lastPrice = findLastPrice(stock);
		final double lastPriceValue = (lastPrice != null ? lastPrice.getValue() : 1.0 + 999.0 * random.nextDouble());
		final double newReturn = 1.0 + random.nextGaussian() * stock.getStdDev();
		final double newPriceValue = lastPriceValue * newReturn;
		final Price price = new Price();
		price.setStock(stock);
		price.setValue(newPriceValue);
		return repository.save(price);
	}

	@Override
	public List<Price> generatePrices(final Stock stock, final int n) {
		final Price lastPrice = findLastPrice(stock);
		double lastPriceValue = (lastPrice != null ? lastPrice.getValue() : 1.0 + 999.0 * random.nextDouble());
		final List<Price> prices = new ArrayList<Price>(n);
		for (int i = 0; i < n; ++i) {
			final double newReturn = 1.0 + random.nextGaussian() * stock.getStdDev();
			final double newPriceValue = lastPriceValue * newReturn;
			final Price price = new Price();
			price.setStock(stock);
			price.setValue(newPriceValue);
			prices.add(price);
			lastPriceValue = newPriceValue;
		}
		return repository.save(prices);
	}

}
