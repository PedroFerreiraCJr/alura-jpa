package loja.dao;

import javax.persistence.EntityManager;

import loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
}
