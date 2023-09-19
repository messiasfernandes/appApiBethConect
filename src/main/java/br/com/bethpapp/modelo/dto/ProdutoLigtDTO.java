package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoLigtDTO {
	private String nomeproduto;
	private EstoqueDTO estoque;
	private BigDecimal precovenda;
}
