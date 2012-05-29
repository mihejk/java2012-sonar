package hr.java2012.sonar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "portfolio_id", "stock_id" }) })
public class Position extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "portfolio_id")
	@NotNull
	private Portfolio portfolio;

	@ManyToOne
	@JoinColumn(name = "stock_id")
	@NotNull
	private Stock stock;

	@NotNull
	private Integer quantity;

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(final Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(final Stock stock) {
		this.stock = stock;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

}
