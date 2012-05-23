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

	@Column(unique = true)
	@NotNull
	@Size(max = 50)
	private String name;

	@NotNull
	private Double mean;

	@NotNull
	private Double stdDev;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Double getMean() {
		return mean;
	}

	public void setMean(final Double mean) {
		this.mean = mean;
	}

	public Double getStdDev() {
		return stdDev;
	}

	public void setStdDev(final Double stdDev) {
		this.stdDev = stdDev;
	}

}
