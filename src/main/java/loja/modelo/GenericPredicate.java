package loja.modelo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface GenericPredicate<T, E> {
	public abstract boolean accept(E value);

	public abstract Predicate apply(CriteriaBuilder builder, Predicate filtros, Root<T> from, E value);
}
