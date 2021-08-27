package loja.modelo;

import javax.persistence.Entity;
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
@Table(name = "livros")
public class Livro extends Produto {

	private static final long serialVersionUID = -3315261101234615078L;

	private String autor;
	private Integer numeroDePaginas;

	public Livro(String autor, Integer numeroDePaginas) {
		super();
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}

	public Livro() {
		super();
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

}
