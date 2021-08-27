package loja.teste;

import javax.persistence.EntityManager;

import loja.modelo.Categoria;
import loja.modelo.CategoriaId;
import loja.util.JpaUtil;

public class CategoriaTest {
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		em.find(Categoria.class, new CategoriaId("Celulares", "xpto"));
	}
}
