package loja.modelo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractEqualPredicate<T, E> implements GenericPredicate<T, E> {

	protected String field;

	public AbstractEqualPredicate(String field) {
		super();
		this.field = field;
	}

	@Override
	public Predicate apply(CriteriaBuilder builder, Predicate filtros, Root<T> from, E value) {
		return builder.and(filtros, builder.equal(from.get(field), value));
	}
}
