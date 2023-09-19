package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class ProdutoComandaDTO {

	private Long id;

	private String nomeproduto;


	@JsonFormat(pattern = "#,##0.00", shape = Shape.STRING)
	@NumberFormat(pattern = "#,##0.00")

	private BigDecimal precovenda;
}
