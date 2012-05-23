package hr.java2012.sonar.repository;

import java.util.List;

import hr.java2012.sonar.model.Portfolio;
import hr.java2012.sonar.model.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	
	List<Position> findByPortfolio(Portfolio portfolio);

}
