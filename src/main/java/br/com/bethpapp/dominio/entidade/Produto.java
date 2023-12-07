package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Produto extends GeradorId {

	private static final long serialVersionUID = 1L;

	@Setter(value = AccessLevel.NONE)
	@NotEmpty
	@Column(length = 120)
	private String nomeproduto;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String marca;
	@Column(length = 6)
	private String unidade;
	@Column(length = 255)
	private String imagemPrincipal;
	@Column(length = 60)
	private String sku;
	@Column(length = 120)
	private String descricao;
	@Column
	private Boolean ativo;
	@Column
	private Boolean controlarestoque;
	@Column(length = 13)
	private String codigoEan13;
	@Column(length = 255)
	private String caracteristica;
	@Column(length = 20)
	private String codigofabricante;
	@Transient
	@Getter(value = AccessLevel.NONE)
	private String caracteristicaAtributo;

	@Digits(integer = 9, fraction = 4)
	@Setter(value = AccessLevel.NONE)
	private BigDecimal precovenda;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal precocusto;

	@Digits(integer = 9, fraction = 4)
	private BigDecimal customedio;
	@Column
	private Integer estoqueminimo;
	@JsonIgnore
	@OneToOne(mappedBy = "produto", fetch = FetchType.LAZY)
	@JoinColumn
	private Estoque estoque;
	@JsonIgnoreProperties(value = { "nomeSubCategoria" }, allowGetters = true)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn
	private SubCategoria subcategoria;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProdutoFornecedor> produtoFonecedores = new HashSet<>();
	@Fetch(FetchMode.SUBSELECT)
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id"))
	@BatchSize(size = 10)
	private Set<Atributo> atributos = new HashSet<>();

	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "produto_componente", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "componente_id"))
	private Set<Componente> componentes;

	public void setNomeproduto(String nomeproduto) {
		if (nomeproduto != null) {
			this.nomeproduto = nomeproduto.toUpperCase();
		} else {
			this.nomeproduto = nomeproduto;
		}

	}

	public void setMarca(String marca) {
		if (marca != null) {
			this.marca = marca.toUpperCase();
		} else {
			this.marca = marca;
		}
	}

	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto.setScale(3, RoundingMode.HALF_UP);

	}

	public void setCustomedio(BigDecimal customedio) {
		this.customedio = customedio.setScale(3, RoundingMode.HALF_UP);

	}

	public void setPrecovenda(BigDecimal precovenda) {
		this.precovenda = precovenda.setScale(3, RoundingMode.HALF_UP);

	}

	@Transient
	private String concatenar() {
		StringBuilder strBuilder = new StringBuilder();

		for (Atributo atributo : atributos) {
			strBuilder.append(atributo.getTipo()).append(" : ").append(atributo.getValor()).append(" | ");
		}

		// Remove o último "| " se houver algum atributo
		if (!atributos.isEmpty()) {
			strBuilder.setLength(strBuilder.length() - 2);
		}

		return strBuilder.toString();
	}

	public String getCaracteristicaAtributo() {
		return concatenar();
	}
}
