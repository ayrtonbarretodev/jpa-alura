package br.com.ayrton.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.ayrton.loja.modelo.Categoria;

public class CategoriaDAO {
	EntityManager em;

	public CategoriaDAO(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar (Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar (Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public Categoria buscarPorId (Long id) {
		Categoria categoria = this.em.find(Categoria.class, id);
		return categoria;
	}
	
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
	
	public List<Categoria> findAll(){
		String jpql = "select c from Categoria c";
		return em.createQuery(jpql,Categoria.class).getResultList();
	}
	
	public void atualizarNome(Categoria categoria,String nome) {
		categoria.setNome(nome);
	}
	
	public Categoria buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT c FROM Categoria c WHERE c.nome = :nome";
		return em.createQuery(jpql, Categoria.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}
