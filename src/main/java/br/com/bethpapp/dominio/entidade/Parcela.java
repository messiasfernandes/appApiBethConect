package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Parcela {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private FormadePagmamento formadePagmamento;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	@Column
	private BigDecimal percentual;
	@Transient
	private BigDecimal totalpercentual;
	@NotNull
	private Integer numeroparcela;
	@NotNull
	private Integer dias;
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual.setScale(4, RoundingMode.HALF_EVEN);
	}
}
