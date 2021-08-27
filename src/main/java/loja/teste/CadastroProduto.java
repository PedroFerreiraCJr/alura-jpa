package loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import loja.dao.CategoriaDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Produto;
import loja.util.JpaUtil;

public class CadastroProduto {
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		dao.buscarPorParametrosComCriteria("PS5", null, null);
	}

	private static void cadastrarProduto() {
		EntityManager em = JpaUtil.getEntityManager();

		Categoria celulares = new Categoria("Celulares", "xpto");
		CategoriaDao categoriaDao = new CategoriaDao(em);

		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		celular.setCategoria(celulares);

		ProdutoDao produtoDao = new ProdutoDao(em);
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

	private static void buscarPorId() {
		Long id = 1l;
		ProdutoDao dao = new ProdutoDao(JpaUtil.getEntityManager());
		Produto p = dao.buscarPorId(id);
		System.out.println(p.getNome());
	}

	private static List<Produto> buscarTodos() {
		ProdutoDao dao = new ProdutoDao(JpaUtil.getEntityManager());
		return dao.buscarTodos();
	}

	private static List<Produto> buscarPorNome() {
		ProdutoDao dao = new ProdutoDao(JpaUtil.getEntityManager());
		return dao.buscarPorNome("Xiaomi Redmi");
	}

	private static List<Produto> buscarPorNomeCategoria() {
		ProdutoDao dao = new ProdutoDao(JpaUtil.getEntityManager());
		return dao.buscarPorNomeCategoria("Celulares");
	}

	private static BigDecimal buscarPrecoProdutoPorNome() {
		ProdutoDao dao = new ProdutoDao(JpaUtil.getEntityManager());
		return dao.buscarPrecoProdutoPorNome("Xiaomi Redmi");
	}
}
