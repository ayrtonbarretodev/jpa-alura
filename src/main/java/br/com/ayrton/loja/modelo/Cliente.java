package br.com.ayrton.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded //integra a classe DadosPessoais na Entidade Cliente
	private DadosPessoais dadosPessoais;

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome,cpf);
	}

}
