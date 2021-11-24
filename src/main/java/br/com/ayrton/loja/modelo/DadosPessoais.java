package br.com.ayrton.loja.modelo;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable //Essa anota��o indica que essa Classe � imbutivel dentro de uma entidade, neste caso a Entidade Cliente
public class DadosPessoais {
	private String nome;
	private String cpf;

}
