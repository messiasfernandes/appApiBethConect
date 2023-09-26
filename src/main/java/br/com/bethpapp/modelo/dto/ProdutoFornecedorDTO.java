package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.bethpapp.dominio.entidade.FornecedorProdutoid;
import lombok.Data;

@Data
public class ProdutoFornecedorDTO {
	private FornecedorProdutoid id;
	private FonecedorProdutoDto fornecedor;
	private LocalDate dataCompra;
	private Integer garantia;
	private BigDecimal valorProduto;

}
