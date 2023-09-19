package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class ImpostoNota {
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal totalNota;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal valorIcms;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal valorIpi;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal valorTributo;

	public ImpostoNota() {
		totalNota = BigDecimal.ZERO;
		valorIcms =BigDecimal.ZERO;
		valorTributo = BigDecimal.ZERO;
		valorIpi = BigDecimal.ZERO;
	}

	public void setTotalNota(BigDecimal totalNota) {
		this.totalNota = totalNota.setScale(4, RoundingMode.HALF_EVEN);
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms.setScale(4, RoundingMode.HALF_EVEN);
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi.setScale(4, RoundingMode.HALF_EVEN);
	}

	public void setValorTributo(BigDecimal valorTributo) {
		this.valorTributo = valorTributo.setScale(4, RoundingMode.HALF_EVEN);
	}
}
