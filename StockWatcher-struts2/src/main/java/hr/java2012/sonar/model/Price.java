package hr.java2012.sonar.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Price extends AbstractEntity {

	@ManyToOne
	@NotNull
	private Stock stock;

	@NotNull
	private Double value;

	public Stock getStock() {
		return stock;
	}

	public void setStock(final Stock stock) {
		this.stock = stock;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(final Double value) {
		this.value = value;
	}

}
