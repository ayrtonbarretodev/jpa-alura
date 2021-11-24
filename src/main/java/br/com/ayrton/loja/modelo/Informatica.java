package br.com.ayrton.loja.modelo;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
/* Exemplo de Herança */
public class Informatica extends Produto {

	private String marca;
	private String modelo;

}
