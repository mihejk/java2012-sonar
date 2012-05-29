package hr.java2012.sonar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Portfolio extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int MAX_SIZE = 50;

	@Column(unique = true)
	@NotNull
	@Size(max = MAX_SIZE)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
