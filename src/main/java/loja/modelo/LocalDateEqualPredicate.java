package loja.modelo;

import java.time.LocalDate;

public class LocalDateEqualPredicate extends AbstractEqualPredicate<Produto, LocalDate> {
	public LocalDateEqualPredicate(String field) {
		super(field);
	}

	@Override
	public boolean accept(LocalDate value) {
		return value != null;
	}
}
