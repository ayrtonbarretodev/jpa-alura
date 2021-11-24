package br.com.ayrton.loja.testes;

import javax.persistence.EntityManager;

import br.com.ayrton.loja.dao.PedidoDAO;
import br.com.ayrton.loja.modelo.Pedido;
import br.com.ayrton.loja.util.JPAUtil;

public class PerformanceConsultas {
	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();

		Pedido pedido = em.find(Pedido.class, 1L);
		System.out.println(pedido.getData());
		System.out.println(pedido.getItens().size());

		/* ------------------------------------ */

		PedidoDAO pedidoDao = new PedidoDAO(em);
		Pedido pedido2 = pedidoDao.buscarPedidoComCliente(2L);
		em.close();
		System.out.println(pedido2);

	}
}
