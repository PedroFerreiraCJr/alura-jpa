package loja.modelo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * #Como utilizar uma chave composta nas entidade
 * 
 * A chave composta é uma classe que mantém os atributos que fazem parte da
 * chave, por exemplo, duas String. A classe deve ser anotada com
 * 
 * <pre>
 * &#64;javax.persistence.Embeddable
 * </pre>
 * 
 * já a classe que declara a chave composta, deve definir o atributo do tipo da
 * classe embutida, mencionada acima e anotá-la com
 * 
 * <pre>
 * &#64;javax.persistence.EmbeddedId
 * </pre>
 * 
 * conforme esta classe de exemplo do mapeamento de chave composta. Uma outra
 * característica que deve ser levada em consideração é que a classe que define
 * a chave composta deve implementar a interface de marcação
 * <strong>java.io.Serializable</strong>.
 * 
 * @author Pedro Junior
 *
 */
/*
 * É uma boa prática tornar as classes que são entidade Serializable
 */
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

	private static final long serialVersionUID = -4742306748004073577L;

	@EmbeddedId
	private CategoriaId id;

	public Categoria(String nome, String tipo) {
		super();
		this.id = new CategoriaId(nome, tipo);
	}

	public Categoria() {
		super();
	}

	public String getNome() {
		return this.id.getNome();
	}

	public String getTipo() {
		return this.id.getTipo();
	}
}
