package hr.java2012.sonar.repository;

import hr.java2012.sonar.model.Portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

	Portfolio findByName(String name);

}
