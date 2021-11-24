package br.com.ayrton.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.ayrton.loja.dao.CategoriaDAO;
import br.com.ayrton.loja.dao.ProdutoDAO;
import br.com.ayrton.loja.modelo.Categoria;
import br.com.ayrton.loja.modelo.Produto;
import br.com.ayrton.loja.util.JPAUtil;

public class CRUDProduto {
	public static void main(String[] args) {

//		Produto produto = buscarProduto(2L);
//		System.out.println(produto.toString());
//
//		List<Produto> produtos = buscarTodos();
//		produtos.forEach(System.out::println);
//
//		List<Produto> produtosPorNome = buscarPorNome("Iphone 12");
//		produtosPorNome.forEach(System.out::println);
//		
//		List<Produto> produtosPorNomeCategoria = buscarPorNomeDaCategoria("COMPUTADORES");
//		produtosPorNomeCategoria.forEach(System.out::println);
//		
		List<Produto> produtosPorNomeCategoria2 = buscarPorNomeDaCategoriaNamedQuery("TABLETS");
		produtosPorNomeCategoria2.forEach(System.out::println);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		List<Produto> resultado = dao.buscarPorParametrosComCriteria("Xiaomi", new BigDecimal("2500"), null);
		resultado.forEach(System.out::println);
		
//		EntityManager em = JPAUtil.getEntityManager();
//		ProdutoDAO dao = new ProdutoDAO(em);
//		List<Produto> resultado = dao.buscarPorParametrosTeste(null, new BigDecimal("2500"), null);
//		resultado.forEach(System.out::println);

	}

	private static void cadastrarProduto(String nome, String descricao, BigDecimal valor, Categoria categoria) {
		Produto produto = new Produto(nome, descricao, valor, categoria);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDAO dao = new ProdutoDAO(em);

		em.getTransaction().begin();
		dao.cadastrar(produto);
		em.getTransaction().commit();
		em.close();
	}

	private static void cadastrarCategoria(String nome) {
		Categoria categoria = new Categoria(nome);

		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		em.getTransaction().begin();
		categoriaDAO.cadastrar(categoria);
		em.getTransaction().commit();
		em.close();
	}

	private static Produto buscarProduto(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		Produto produto = dao.buscarPorId(id);
		return produto;
	}

	private static List<Produto> buscarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		return dao.findAll();
	}

	private static List<Produto> buscarPorNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		return dao.buscarPorNome(nome);
	}

	private static List<Produto> buscarPorNomeDescricao(String nome, String descricao) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		return dao.buscarPorNomeDescricao(nome, descricao);
	}

	private static List<Produto> buscarPorNomeDaCategoria(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		return dao.buscarPorNomeDaCategoria(nome);
	}

	private static Categoria buscarCategoria(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO dao = new CategoriaDAO(em);
		return dao.buscarPorNomeDaCategoria(nome);
	}

	private static BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		return dao.buscarPrecoDoProdutoComNome(nome);
	}
	
	private static List<Produto> buscarPorNomeDaCategoriaNamedQuery(String nome) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		return dao.buscarPorNomeDaCategoriaNamedQuery(nome);
	}
}
