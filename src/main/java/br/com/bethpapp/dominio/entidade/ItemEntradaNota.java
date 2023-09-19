package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ItemEntradaNota extends GeradorId {

	private static final long serialVersionUID = 1L;

	public ItemEntradaNota() {
		subtotal = BigDecimal.ZERO;
	///	desconto = BigDecimal.ZERO;
	}

	private Integer qtde;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal subtotal;
	//@Setter(value = AccessLevel.NONE)
//	@Digits(integer = 9, fraction = 4)
//	private BigDecimal desconto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private EntradaNotaCabecario entradaNotafiscal;

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal.setScale(4, RoundingMode.HALF_UP);
	}




//	public void setDesconto(BigDecimal desconto) {
//		this.desconto = desconto.setScale(4, RoundingMode.HALF_UP);
//	}

}
