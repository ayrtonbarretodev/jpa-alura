package br.com.ayrton.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.ayrton.loja.modelo.Pedido;
import br.com.ayrton.loja.vo.RelatorioDeVendasVo;

public class PedidoDAO {
	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return this.em.find(Pedido.class, id);
	}

	public void remover(Pedido pedido) {
		pedido = em.merge(pedido);
		this.em.remove(pedido);
	}

	public List<Pedido> findAll() {
		String jpql = "select p from Pedido p";
		return em.createQuery(jpql, Pedido.class).getResultList();
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM (p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	
	public BigDecimal menorValorVendido() {
		String jpql = "SELECT MIN (p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	
	public BigDecimal maiorValorVendido() {
		String jpql = "SELECT MAX (p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	
	
	public List<RelatorioDeVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.ayrton.loja.vo.RelatorioDeVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY SUM (item.quantidade) DESC";
		return em.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
		
		/* Os dados que estão vindos do banco de dados são recebidos pelos  
		 * atributos da classe RelatorioDeVendasVo e recebidos em uma Lista 
		 *  */
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
		
		/* O join fetch permite escolher quais relacionamentos serão carregados em determinada consulta, 
		 * ao invés de sempre os carregar */
		
		return em.createQuery(jpql,Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}