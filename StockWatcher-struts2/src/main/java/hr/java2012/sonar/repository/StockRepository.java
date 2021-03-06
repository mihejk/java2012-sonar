package hr.java2012.sonar.repository;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	Stock findByTicker(String ticker);

	@Query("select s from Stock s where s not in (select p.stock from Position p where p.portfolio = :portfolio)")
	List<Stock> findByNotInPortfolio(@Param("portfolio") Portfolio portfolio);

}
