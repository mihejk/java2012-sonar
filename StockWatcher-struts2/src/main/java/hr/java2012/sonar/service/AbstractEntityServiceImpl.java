package hr.java2012.sonar.service;

import hr.java2012.sonar.model.AbstractEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class AbstractEntityServiceImpl<T extends AbstractEntity> implements AbstractEntityService<T> {

	private final JpaRepository<T, Long> repository;

	public AbstractEntityServiceImpl(final JpaRepository<T, Long> repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public T save(final T entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public List<T> save(final Iterable<T> entities) {
		return repository.save(entities);
	}

	@Transactional
	@Override
	public void delete(final T entity) {
		repository.delete(entity);
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public T findOne(final Long id) {
		return repository.findOne(id);
	}

}
