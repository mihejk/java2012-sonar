package hr.java2012.sonar.service;

import hr.java2012.sonar.model.AbstractEntity;

import java.util.List;

public interface AbstractEntityService<T extends AbstractEntity> {

	T save(T entity);

	List<T> save(Iterable<T> entities);

	void delete(T entity);

	List<T> findAll();

	long count();

	T findOne(Long id);

}
