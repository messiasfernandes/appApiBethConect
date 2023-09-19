package br.com.bethpapp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class PrecoComJuros {

	private BigDecimal valorBase;
	private BigDecimal valorParcelado; // pv
	private BigDecimal entrada;
	private int numeroParcelas;
	private BigDecimal taxaJuros;
	private BigDecimal valorParcela;
	private BigDecimal valorTotal; // pmt
	private BigDecimal valorJuros;

	public PrecoComJuros(BigDecimal valorBase, BigDecimal entrada, int numeroParcelas, BigDecimal taxaJuros) {
		if (numeroParcelas <= 0)
			throw new IllegalArgumentException();
		if (taxaJuros.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException();
		this.valorBase = valorBase;
		this.entrada = entrada;
		this.numeroParcelas = numeroParcelas;
		this.taxaJuros = taxaJuros;
		BigDecimal juros = taxaJuros.divide(new BigDecimal(100)); // i
		this.valorParcelado = valorBase.subtract(entrada);
		if (taxaJuros.compareTo(BigDecimal.ZERO) == 0) {
			this.valorParcela = valorParcelado.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_EVEN);
		} else {
			BigDecimal potencia = juros.add(BigDecimal.ONE).pow(numeroParcelas);
			BigDecimal denominador = BigDecimal.ONE
					.subtract(BigDecimal.ONE.divide(potencia, 20, RoundingMode.HALF_EVEN));
			this.valorParcela = valorParcelado.multiply(juros).divide(denominador, 2, RoundingMode.HALF_EVEN);
		}
		this.valorJuros = valorParcela.multiply(BigDecimal.valueOf(numeroParcelas));
		this.valorTotal = entrada.add(valorJuros);
	}

	public BigDecimal getValorBase() {
		return valorBase;
	}

	public BigDecimal getValorParcelado() {
		return valorParcelado;
	}

	public BigDecimal getEntrada() {
		return entrada;
	}

	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public BigDecimal getValorJuros() {
		return valorJuros;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(" : : ".split(":")));
	    PrecoComJuros p = new PrecoComJuros(BigDecimal.valueOf(30_000), BigDecimal.valueOf(10_000), 24, BigDecimal.valueOf(5));
	    System.out.println("Valor da parcela: " + p.getValorParcela());
	    System.out.println("Juros total: " + p.getValorJuros());
		    System.out.println("Valor total: " + p.getValorTotal());
	}
}