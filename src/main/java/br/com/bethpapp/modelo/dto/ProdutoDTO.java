package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ProdutoDTO {

	private SubCategoriaDTO subcategoria;

	private Long id;

	private String nomeproduto;

	private String marca;

	private String unidade;

	private String imagemPrincipal;

	private String sku;
	private Integer estoqueminimo;
    private String descricao;

	private Boolean ativo;
	private String codigoEan13;
	private Boolean controlarestoque;
	private String caracteristica;
	private String caracteristicaAtributo;
	private String codigofabricante;
	@JsonFormat(pattern = "#,##0.00", shape = Shape.STRING)
	@NumberFormat(pattern = "#,##0.00")

	private BigDecimal precovenda;
	@JsonFormat(pattern = "#,##0.00", shape = Shape.STRING)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal precocusto;
	@JsonFormat(pattern = "#,##0.00", shape = Shape.STRING)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal customedio;
	private EstoqueDTO estoque;
	private Set<ProdutoFornecedorDTO> produtoFonecedores = new HashSet<>();

	private Set<AtributoDTO> atributos = new HashSet<>();
	private Set<ComponeteDTO> componentes;
}
