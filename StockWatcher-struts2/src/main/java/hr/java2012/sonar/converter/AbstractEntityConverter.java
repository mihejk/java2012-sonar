package hr.java2012.sonar.converter;

import hr.java2012.sonar.service.AbstractEntityService;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class AbstractEntityConverter<T extends hr.java2012.sonar.model.AbstractEntity> extends StrutsTypeConverter {

	private final Class<T> entityClass;

	private final AbstractEntityService<T> entityService;

	public AbstractEntityConverter(final Class<T> entityClass, final AbstractEntityService<T> entityService) {
		this.entityClass = entityClass;
		this.entityService = entityService;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object convertFromString(final Map context, final String[] values, final Class toClass) {
		final Long id = Long.parseLong(values[0]);
		return entityService.findOne(id);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String convertToString(final Map context, final Object o) {
		final T entity = entityClass.cast(o);
		return entity.getId().toString();
	}

}
