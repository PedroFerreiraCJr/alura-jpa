package loja.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

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
@Embeddable
public class CategoriaId implements Serializable {

	private static final long serialVersionUID = 6465611908957457561L;

	private String nome;
	private String tipo;

	public CategoriaId(String nome, String tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	public CategoriaId() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}
}
