package br.com.bethpapp.modelo.input;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.bethpapp.dominio.entidade.Atributo;
import br.com.bethpapp.dominio.entidade.Componente;
import br.com.bethpapp.dominio.entidade.ProdutoFornecedor;
import lombok.Data;

@Data
@JsonInclude(content = Include.NON_NULL)
public class ProdutoInput {

	private Long id;

	private String nomeproduto;

	private String marca;

	private String unidade;

	private String imagemPrincipal;

	private String sku;
	private Integer estoqueminimo;
	private String codigoEan13;
	private String descricao;

	private Boolean ativo;
	private String caracteristica;

	private String codigofabricante;
	private Boolean controlarestoque;
	private BigDecimal precovenda;

	private BigDecimal precocusto;

	private BigDecimal customedio;
	private SubCategoriaInput subcategoria;
	private Set<ProdutoFornecedor> produtoFonecedores = new HashSet<>();
	private List<Atributo> atributos;
	private List<Componente> componentes;

}
