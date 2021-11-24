package br.com.ayrton.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "itens")
@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();

	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@ManyToOne (fetch = FetchType.LAZY)
	private Cliente cliente;

	// colocar o mappedBy sempre no lado do *ToMany
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public void adicionarItem(ItemPedido itemPedido) {
		/* setando o pedido e adicionado ele na lista */
		itemPedido.setPedido(this);
		this.itens.add(itemPedido);
		this.valorTotal = this.valorTotal.add(itemPedido.getValor());
	}
	
	

}
