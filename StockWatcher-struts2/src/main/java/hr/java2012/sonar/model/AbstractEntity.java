package hr.java2012.sonar.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

}
