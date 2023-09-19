/**
 * 
 */
package br.com.bethpapp.modelo.input;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class ProdutoComponenteInput {
	private Long id;
	private String nomeproduto;

	private String unidade;
	


	@Digits(integer = 9, fraction = 4)
	@Setter(value = AccessLevel.NONE)
	private BigDecimal precovenda;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	private BigDecimal precocusto;

	@Digits(integer = 9, fraction = 4)
	private BigDecimal customedio;
	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto.setScale(3, RoundingMode.HALF_UP);

	}

	public void setCustomedio(BigDecimal customedio) {
		this.customedio = customedio.setScale(3, RoundingMode.HALF_UP);

	}

	public void setPrecovenda(BigDecimal precovenda) {
		this.precovenda = precovenda.setScale(3, RoundingMode.HALF_UP);

	}
}
