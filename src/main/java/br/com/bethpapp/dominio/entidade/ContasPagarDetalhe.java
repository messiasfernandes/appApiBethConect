package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.enumerado.StatusPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ContasPagarDetalhe extends GeradorId implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	private String numtitulo;
	@NotNull
	private Integer numparcela;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoparcela;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valoprago;
	@Setter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorapagar;
	private BigDecimal taxaJuro;
	private BigDecimal taxadesConto;
	@ManyToOne
	private ContasPagar contasaPagar;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate descontoAte;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datadePagamento;
	@ManyToOne
	private FormadePagmamento formadePagamento;
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private StatusPagamento statusPagmaento;

	public void setValoparcela(BigDecimal valoparcela) {
		this.valoparcela = valoparcela.setScale(4, RoundingMode.HALF_UP);
	}

	public void setValoprago(BigDecimal valoprago) {
		this.valoprago = valoprago.setScale(4, RoundingMode.HALF_UP);
	}

	public void setValorapagar(BigDecimal valorapagar) {
		this.valorapagar = valorapagar.setScale(4, RoundingMode.HALF_UP);
	}
}
