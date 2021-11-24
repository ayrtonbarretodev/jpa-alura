package br.com.ayrton.loja.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* Essa � uma classe DTO
 * 
 * DTO - Data Transfer Object
 * 
 * Padr�o de projetos para transporte de dados entre diferentes componentes de um
 * sistema.
 * 
 * Como funciona?
 * Agrupando um conjunto de atributos em uma classe simples de forma a otimizar
 * a comunica��o.
 * 
 * 
 * */


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RelatorioDeVendasVo {
	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;
}
