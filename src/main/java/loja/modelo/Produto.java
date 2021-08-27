package loja.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * #Principais estratégias de mapeamento de herança entre entidades.
 * 
 * A primeira estratégia de mapeamento de herança nas classes que são entidades
 * é o de <strong>Single Table</strong>. Nesta estratégia, uma única tabela é
 * criada para armazenar todos os dados de cada subclasse mapeada. A principal
 * vantagem dessa abordagem é a performance, pois todos os dados ficam na mesma
 * tabela, não precisando de join nas consultas. Na classe base, ou seja
 * <strong>Produto</strong> é preciso adicionar a seguinte anotação:
 * 
 * <pre>
 * &#64;Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 * </pre>
 * 
 * A segunda estratégia de mapeamento de herança é a <strong>Joined</strong>.
 * Nesta abordagem cada subclasse é mapeada para uma tabela, onde cada nesta
 * tabela são definidos somente os atributos da subclasse, fazendo assim a
 * separação dos dados de cada entidade em uma tabela separada. Quando é feito
 * uma consulta nas entidade mapeadas da classe ou entidade base, um join é
 * utilizado para trazer os dados em comum a todas as entidades. Para utilizar
 * essa estratégia, na classe base é preciso adicionar a seguinte anotação:
 * 
 * <pre>
 * &#64;Inheritance(strategy = InheritanceType.JOINED)
 * </pre>
 * 
 * @author Pedro Junior
 *
 */
/*
 * É uma boa prática tornar as classes que são entidade Serializable
 */
@Entity
@Table(name = "produtos")
@NamedQuery(name = "Produto.produtosPorCategoria", query = "SELECT p FROM Produto p WHERE p.categoria.id.nome = :nomeCategoria")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto implements Serializable {

	private static final long serialVersionUID = -4659372171622330577L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();

	/*
	 * Relacionamentos que são terminados com One, por padrão são carregados de
	 * forma Eager. Como boa prática, é necesssário alterar a forma de carregamento
	 * do relacionamento para Lazy. Caso em algum momento seja necessário obter os
	 * dados do objeto do relacionamento que foi alterado para LAZY, será preciso
	 * criar uma consulta planejada.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produto [id=").append(id).append(", nome=").append(nome).append(", descricao=")
				.append(descricao).append("]");
		return builder.toString();
	}
}
