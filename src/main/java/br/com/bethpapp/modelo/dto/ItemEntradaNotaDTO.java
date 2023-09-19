package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ItemEntradaNotaDTO {
	private Long id;
	private Integer qtde;

	private BigDecimal subtotal;
	private BigDecimal desconto;
	private ProdutoDTO produto;
	
	
	
}