package loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import loja.dao.CategoriaDao;
import loja.dao.ClienteDao;
import loja.dao.PedidoDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.modelo.Cliente;
import loja.modelo.ItemPedido;
import loja.modelo.Pedido;
import loja.modelo.Produto;
import loja.util.JpaUtil;
import loja.vo.RelatorioVendaVO;

public class CadastroPedido {

	public static void main(String[] args) {
		cadastrarProduto();

		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorId(1l);

		em.getTransaction().begin();
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = new Cliente("Rodrigo", "000.000.000-00");
		clienteDao.cadastrar(cliente);

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();

		BigDecimal total = pedidoDao.valorTotalVendido();
		System.out.println("Total Vendido: " + total);

		List<RelatorioVendaVO> relatorio = pedidoDao.relatorioVendas();
		relatorio.forEach(System.out::println);
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
}
