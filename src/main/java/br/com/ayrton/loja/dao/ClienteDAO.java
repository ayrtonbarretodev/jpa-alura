package br.com.ayrton.loja.dao;

import javax.persistence.EntityManager;

import br.com.ayrton.loja.modelo.Cliente;

public class ClienteDAO {
	private EntityManager em;

	public ClienteDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar (Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public Cliente buscarPorId (Long id) {
		return this.em.find(Cliente.class, id); 
	}
	
	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		this.em.remove(cliente);
	}
}
