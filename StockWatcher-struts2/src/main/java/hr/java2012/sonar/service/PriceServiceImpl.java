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
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository repository;

	private Random random = new Random();

	@Override
	public Price findLastPrice(Stock stock) {
		return repository.findLastByStock(stock);
	}

	@Override
	public List<Price> findLastPrices(Stock stock, int n) {
		PageRequest pageRequest = new PageRequest(0, n, Sort.Direction.DESC, "id");
		Page<Price> page = repository.findByStock(stock, pageRequest);
		return page.getContent();
	}

	@Override
	public long countPrices(Stock stock) {
		return repository.countByStock(stock);
	}

	@Override
	public Price generatePrice(Stock stock) {
		Price lastPrice = findLastPrice(stock);
		double lastPriceValue = (lastPrice != null ? lastPrice.getValue() : 1.0 + 999.0 * random.nextDouble());
		double newReturn = 1.0 + random.nextGaussian() * stock.getStdDev();
		double newPriceValue = lastPriceValue * newReturn;
		Price price = new Price();
		price.setStock(stock);
		price.setValue(newPriceValue);
		return repository.save(price);
	}

	@Override
	public List<Price> generatePrices(Stock stock, int n) {
		Price lastPrice = findLastPrice(stock);
		double lastPriceValue = (lastPrice != null ? lastPrice.getValue() : 1.0 + 999.0 * random.nextDouble());
		List<Price> prices = new ArrayList<Price>(n);
		for (int i = 0; i < n; ++i) {
			double newReturn = 1.0 + random.nextGaussian() * stock.getStdDev();
			double newPriceValue = lastPriceValue * newReturn;
			Price price = new Price();
			price.setStock(stock);
			price.setValue(newPriceValue);
			prices.add(price);
			lastPriceValue = newPriceValue;
		}
		return repository.save(prices);
	}

}
