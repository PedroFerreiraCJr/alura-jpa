package loja.modelo;

import java.math.BigDecimal;

public class BigDecimalEqualPredicate extends AbstractEqualPredicate<Produto, BigDecimal> {
	public BigDecimalEqualPredicate(String field) {
		super(field);
	}

	@Override
	public boolean accept(BigDecimal value) {
		return value != null;
	}
}
