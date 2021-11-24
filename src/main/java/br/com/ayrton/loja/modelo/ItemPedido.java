package br.com.ayrton.loja.modelo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	private Integer quantidade;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Produto produto;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Pedido pedido;

	public ItemPedido(Integer quantidade, Produto produto, Pedido pedido) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
		this.precoUnitario = produto.getPreco();
	}

	public BigDecimal getValor() {
		return this.precoUnitario.multiply(new BigDecimal(quantidade));
	}
	
}
