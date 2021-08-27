package loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import loja.modelo.BigDecimalEqualPredicate;
import loja.modelo.LocalDateEqualPredicate;
import loja.modelo.Produto;
import loja.modelo.StringEqualPredicate;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "FROM Produto";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		return query.getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	public List<Produto> buscarPorNome(String nome, int limit) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		query.setParameter("nome", nome);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	public BigDecimal buscarPrecoProdutoPorNome(String nome) {
		String jpql = "SELECT DISTINCT p.preco FROM Produto p WHERE p.nome = :nome";
		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
		query.setParameter("nome", nome);
		return query.getSingleResult();
	}

	public List<Produto> buscarPorNomeCategoria(String nome) {
		TypedQuery<Produto> query = em.createNamedQuery("Produto.produtosPorCategoria", Produto.class);
		query.setParameter("nomeCategoria", nome);
		return query.getResultList();
	}

	// @formatter:off
	// forma inadequada de criar consultas com parâmetros dinâmicos ou opcionais
	/*
	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1";
		
		if (nome != null && !nome.trim().isEmpty()) {
			jpql += "AND p.nome = :nome";
		}
		
		if (preco != null) {
			jpql += "AND p.preco = :preco";
		}
		
		if (dataCadastro != null) {
			jpql += "AND p.dataCadastro = :dataCadastro";
		}
		
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}
		if (preco != null) {
			query.setParameter("preco", preco);
		}
		if (dataCadastro != null) {
			query.setParameter("dataCadastro", dataCadastro);
		}
		return query.getResultList();
	}
	*/
	// @formatter:on

	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);

		Predicate filtros = builder.and();
		filtros = filtrarPorNomeProduto(builder, from, filtros, nome);
		filtros = filtrarPorPrecoProduto(builder, from, filtros, preco);
		filtros = filtrarPorDataCadastroProduto(builder, from, filtros, dataCadastro);
		query.where(filtros);

		return em.createQuery(query).getResultList();
	}

	private Predicate filtrarPorNomeProduto(CriteriaBuilder builder, Root<Produto> from, Predicate filtros,
			String value) {
		StringEqualPredicate criteriaNome = new StringEqualPredicate("nome");
		if (criteriaNome.accept(value)) {
			filtros = criteriaNome.apply(builder, filtros, from, value);
		}
		return filtros;
	}

	private Predicate filtrarPorPrecoProduto(CriteriaBuilder builder, Root<Produto> from, Predicate filtros,
			BigDecimal value) {
		BigDecimalEqualPredicate criteriaPreco = new BigDecimalEqualPredicate("preco");
		if (criteriaPreco.accept(value)) {
			filtros = criteriaPreco.apply(builder, filtros, from, value);
		}
		return filtros;
	}

	private Predicate filtrarPorDataCadastroProduto(CriteriaBuilder builder, Root<Produto> from, Predicate filtros,
			LocalDate value) {
		LocalDateEqualPredicate criteriaDataCadastro = new LocalDateEqualPredicate("dataCadastro");
		if (criteriaDataCadastro.accept(value)) {
			filtros = criteriaDataCadastro.apply(builder, filtros, from, value);
		}
		return filtros;
	}
}
