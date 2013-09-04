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
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PriceServiceImpl extends AbstractEntityServiceImpl<Price, PriceRepository> implements PriceService {

	private static final double PRICE_RANGE = 999.0;

	private final Random random = new Random();

	@Autowired
	public PriceServiceImpl(final PriceRepository repository) {
		super(repository);
	}

	@Override
	public Price findLastPrice(final Stock stock) {
		return getRepository().findLastByStock(stock);
	}

	@Override
	public List<Price> findLastPrices(final Stock stock, final int n) {
		final PageRequest pageRequest = new PageRequest(0, n, Sort.Direction.DESC, "id");
		final Page<Price> page = getRepository().findByStock(stock, pageRequest);
		return page.getContent();
	}

	@Override
	public long countPrices(final Stock stock) {
		return getRepository().countByStock(stock);
	}

	@Transactional
	@Override
	public Price generatePrice(final Stock stock) {
		final Price lastPrice = findLastPrice(stock);
		final double lastValue = extractOrGenerateValue(lastPrice);
		final Price price = generatePrice(stock, lastValue);
		return getRepository().save(price);
	}

	@Transactional
	@Override
	public List<Price> generatePrices(final Stock stock, final int n) {
		final List<Price> prices = new ArrayList<Price>(n);
		final Price lastPrice = findLastPrice(stock);
		double lastValue = extractOrGenerateValue(lastPrice);
		for (int i = 0; i < n; ++i) {
			final Price price = generatePrice(stock, lastValue);
			lastValue = price.getValue();
			prices.add(price);
		}
		return getRepository().save(prices);
	}

	private double extractOrGenerateValue(final Price price) {
		return (price != null ? price.getValue() : 1.0 + PRICE_RANGE * random.nextDouble());
	}

	private Price generatePrice(final Stock stock, final double lastValue) {
		final double newReturn = 1.0 + random.nextGaussian() * stock.getStdDev();
		final double newValue = lastValue * newReturn;
		final Price price = new Price();
		price.setStock(stock);
		price.setValue(newValue);
		return price;
	}

}
