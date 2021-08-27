package loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * É uma boa prática tornar as classes que são entidade Serializable
 */
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	private Integer quantidade;

	/*
	 * Relacionamentos que são terminados com One, por padrão são carregados de
	 * forma Eager. Como boa prática, é necesssário alterar a forma de carregamento
	 * do relacionamento para Lazy. Caso em algum momento seja necessário obter os
	 * dados do objeto do relacionamento que foi alterado para LAZY, será preciso
	 * criar uma consulta planejada.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;

	/*
	 * Relacionamentos que são terminados com One, por padrão são carregados de
	 * forma Eager. Como boa prática, é necesssário alterar a forma de carregamento
	 * do relacionamento para Lazy. Caso em algum momento seja necessário obter os
	 * dados do objeto do relacionamento que foi alterado para LAZY, será preciso
	 * criar uma consulta planejada.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	public ItemPedido(Integer quantidade, Pedido pedido, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
	}

	public ItemPedido() {
		super();
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return precoUnitario.multiply(new BigDecimal(quantidade));
	}
}
