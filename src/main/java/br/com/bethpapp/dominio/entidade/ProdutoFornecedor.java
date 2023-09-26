package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ProdutoFornecedor implements Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private FornecedorProdutoid id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "produto_id", insertable = false, updatable = false)
	private Produto produto;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "fornecedor_id", insertable = false, updatable = false)
	private Fornecedor fornecedor;
	private LocalDate dataCompra;
	private Integer garantia;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal valorProduto;

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto.setScale(4, RoundingMode.HALF_EVEN);
	}

}
