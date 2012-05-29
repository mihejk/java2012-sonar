package hr.java2012.sonar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Price extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

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
