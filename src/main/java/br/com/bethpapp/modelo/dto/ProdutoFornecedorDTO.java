package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ProdutoFornecedorDTO {
	private Long id;
	private FonecedorProdutoDto fornecedor;
	private LocalDate dataCompra;

	private BigDecimal valorProduto;

}
