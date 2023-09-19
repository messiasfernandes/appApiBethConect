package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;
@Data
public class ItemdaComadaDTO {
	
	
	private ProdutoComandaDTO produto;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal quantidade;
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal subtotal;

}
