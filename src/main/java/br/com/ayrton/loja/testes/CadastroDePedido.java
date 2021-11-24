package br.com.ayrton.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.ayrton.loja.dao.ClienteDAO;
import br.com.ayrton.loja.dao.PedidoDAO;
import br.com.ayrton.loja.dao.ProdutoDAO;
import br.com.ayrton.loja.modelo.Cliente;
import br.com.ayrton.loja.modelo.ItemPedido;
import br.com.ayrton.loja.modelo.Pedido;
import br.com.ayrton.loja.modelo.Produto;
import br.com.ayrton.loja.util.JPAUtil;
import br.com.ayrton.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {

		//Produto produto = buscarProduto(3L);
		//Produto produto2 = buscarProduto(2L);
		//Produto produto3 = buscarProduto(2L);

		//Cliente cliente = new Cliente("Aldeneide Hilário", "43567899876");

		//cadastrarCliente(cliente);

		//cadastrarPedido(produto, cliente, 10);
		
//		buscarValorTotalVendido();
//		buscarMenorValorPedido();
//		buscarMaiorValorPedido();
		
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDAO dao = new PedidoDAO(em);
		
		List<RelatorioDeVendasVo> relatorios = dao.relatorioDeVendas();
		
		relatorios.forEach(System.out::println);

	}

	private static void buscarValorTotalVendido() {
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDAO dao = new PedidoDAO(em);
		BigDecimal valor = dao.valorTotalVendido();
		System.out.println("O valor total vendido é: " + valor);
	}
	
	private static void buscarMenorValorPedido() {
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDAO dao = new PedidoDAO(em);
		BigDecimal valor = dao.menorValorVendido();
		System.out.println("O valor do menor pedido vendido é: " + valor);
	}
	
	private static void buscarMaiorValorPedido() {
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDAO dao = new PedidoDAO(em);
		BigDecimal valor = dao.maiorValorVendido();
		System.out.println("O valor do maior pedido vendido é: " + valor);
	}

	private static void cadastrarPedido(Produto produto, Cliente cliente, Integer quantidade) {
		Pedido pedido = new Pedido(cliente);

		pedido.adicionarItem(new ItemPedido(quantidade, produto, pedido));
		EntityManager em = JPAUtil.getEntityManager();

		PedidoDAO dao = new PedidoDAO(em);

		em.getTransaction().begin();
		dao.cadastrar(pedido);
		em.getTransaction().commit();
		em.close();
	}

	private static Produto buscarProduto(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		Produto produto = dao.buscarPorId(id);
		return produto;
	}

	private static void cadastrarCliente(Cliente cliente) {
		EntityManager em = JPAUtil.getEntityManager();
		ClienteDAO dao = new ClienteDAO(em);

		em.getTransaction().begin();
		dao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
	}

}
