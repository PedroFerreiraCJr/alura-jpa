package loja.modelo;

public class StringEqualPredicate extends AbstractEqualPredicate<Produto, String> {
	public StringEqualPredicate(String field) {
		super(field);
	}

	@Override
	public boolean accept(String value) {
		return value != null && !value.trim().isEmpty();
	}
}
