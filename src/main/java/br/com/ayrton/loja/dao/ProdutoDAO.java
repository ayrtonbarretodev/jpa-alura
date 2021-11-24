package br.com.ayrton.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ayrton.loja.modelo.Produto;

public class ProdutoDAO {
	private EntityManager em;

	public ProdutoDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}

	public List<Produto> findAll() {
		String jpql = "select p from Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public List<Produto> buscarPorNomeDescricao(String nome, String descricao) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome AND p.descricacao = :descricao";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).setParameter("descricao", descricao)
				.getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}

	public List<Produto> buscarPorNomeDaCategoriaNamedQuery(String nome) {
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	// Consultas com parâmetros dinâmicos sem CriteriaAPI
	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";

		if (nome != null && !nome.trim().isEmpty()) {
			jpql = "AND p.nome = :nome ";
		}

		if (preco != null) {
			jpql = "AND p.preco = :preco ";
		}

		if (dataCadastro != null) {
			jpql = "AND p.dataCadastro = :dataCadastro ";
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

	// Consultas com parâmetros dinâmicos com CriteriaAPI
	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);

		Predicate filtros = builder.and();

		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}

		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}

		if (dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}

		query.where(filtros);

		return em.createQuery(query).getResultList();

	}

	public List<Produto> buscarPorParametrosTeste(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) "
				+ " AND (:preco IS NULL OR p.preco = :preco) "
				+ " AND (:dataCadastro IS NULL OR p.dataCadastro = :dataCadastro)";

		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).setParameter("preco", preco)
				.setParameter("dataCadastro", dataCadastro).getResultList();
	}

}
