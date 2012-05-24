package hr.java2012.sonar.repository;

import hr.java2012.sonar.model.Price;
import hr.java2012.sonar.model.Stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	@Query("select p from Price p where p.id = (select max(id) from Price where stock = :stock)")
	Price findLastByStock(@Param("stock") Stock stock);

	Page<Price> findByStock(Stock stock, Pageable pageable);

}
