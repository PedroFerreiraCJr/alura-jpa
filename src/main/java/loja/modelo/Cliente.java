package loja.modelo;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * #Utilizando @Embedded e @Embeddable A annotation
 * 
 * <pre>
 * &#64;javax.persistence.Embedded
 * </pre>
 * 
 * conjuntamento com a annotation
 * 
 * <pre>
 * &#64;javax.persistence.Embeddable
 * </pre>
 * 
 * diz para o processador de annotation, no caso, a implementação da JPA - neste
 * caso deste projeto, o Hibernate - que a classe composta de um atributo
 * anotado com
 * 
 * <pre>
 * &#64;javax.persistence.Embedded
 * </pre>
 * 
 * , onde a classe que define o tipo do atributo deve ser anotada com
 * 
 * <pre>
 * &#64;javax.persistence.Embeddable
 * </pre>
 * 
 * e que esta não é uma entidade que possui atributos próprios, mas uma
 * abstração da orientação a objetos que terá os seus atributos como parte da
 * mesma tabela da classe que possui este atributo.
 * 
 * @author Pedro Junior
 *
 */
/*
 * É uma boa prática tornar as classes que são entidade Serializable
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7128458106323412287L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente(String nome, String cpf) {
		super();
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public Cliente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.dadosPessoais.getNome();
	}

	public String getCpf() {
		return this.dadosPessoais.getCpf();
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
}
