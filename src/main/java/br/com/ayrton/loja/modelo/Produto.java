package br.com.ayrton.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "itens")
@Entity
@Table(name = "produtos")
@NamedQuery(name = "Produto.produtosPorCategoria", query = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorOptions(force = true)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricacao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> itens = new ArrayList<>();

	/*
	 * @OneToMany
	 * 
	 * @ManyToMany
	 * 
	 * Relacionamentos *ToMany n�o tem a caracter�stica de quando ser carregado a
	 * classe (nesse exemplo, Pedido) serem carregados tb.
	 * 
	 * S� ser� acessado caso seja requisitado alguma consulta de "itens" por exemplo
	 */

	/*
	 * @OneToOne
	 * 
	 * @ManyToOne
	 * 
	 * @ManyToOne
	 * 
	 * Relacionamentos *ToOne � carregado automaticamente assim que a classe Pedido
	 * recebe uma consulta.
	 * 
	 * Ser� carregado automaticamente mesmo que n�o seja utilizado nenhum dado da
	 * categoria
	 * 
	 */

	/*
	 * Eager (ansioso): Todo relacionamento to one, o padr�o � ele ser eager, ele
	 * faz o carregamento antecipado.
	 * 
	 * 
	 * Lazy (pregui�oso): J� os relacionamentos to many, por padr�o, o comportamento
	 * � chamado de lazy, que � o carregamento pregui�oso, o carregamento tardio.
	 */

	public Produto(String nome, String descricacao, BigDecimal preco, Categoria categoria) {
		super();
		this.nome = nome;
		this.descricacao = descricacao;
		this.preco = preco;
		this.categoria = categoria;
	}

}
