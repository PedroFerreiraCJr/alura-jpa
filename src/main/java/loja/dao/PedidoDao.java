package loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import loja.modelo.Pedido;
import loja.vo.RelatorioVendaVO;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	// @formatter:off
	/*
	public List<Object[]> relatorioVendas() {
		String jpql = "SELECT pr.nome, SUM(it.quantidade), MAX(pe.dataCriacao) FROM Pedido pe JOIN pe.itens it JOIN it.produto pr GROUP BY pr.nome ORDER BY it.quantidade DESC";
		return em.createQuery(jpql, Object[].class).getResultList();
	}
	*/
	// @formatter:on

	public List<RelatorioVendaVO> relatorioVendas() {
		String jpql = "SELECT new loja.vo.RelatorioVendaVO(pr.nome, SUM(it.quantidade), MAX(pe.dataCriacao)) FROM Pedido pe JOIN pe.itens it JOIN it.produto pr GROUP BY pr.nome ORDER BY it.quantidade DESC";
		return em.createQuery(jpql, RelatorioVendaVO.class).getResultList();
	}

	// Utiliza o Join Fetch para carregacar o cliente que está com o relacionamento
	// LAZY
	public Pedido buscarPedidoComCliente(Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
		return em.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult();
	}
}