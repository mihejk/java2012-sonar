package hr.java2012.sonar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Stock extends AbstractEntity {

	@Column(unique = true)
	@NotNull
	@Size(max = 50)
	private String ticker;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

}
