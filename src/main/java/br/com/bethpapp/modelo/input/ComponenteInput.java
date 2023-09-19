package br.com.bethpapp.modelo.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ComponenteInput {

	private BigDecimal qtde;

	private BigDecimal subtotal;
	
	private ProdutoComponenteInput produto;
}
