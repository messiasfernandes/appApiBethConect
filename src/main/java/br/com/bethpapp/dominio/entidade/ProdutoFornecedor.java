package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ProdutoFornecedor extends GeradorId {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)

	private Produto produto;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)

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
