package br.com.bethpapp.dominio.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.bethpapp.dominio.enumerado.StatusEntradaNota;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EntradaNotaCabecario extends GeradorId {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length = 25)
	private String numerodanota;
	@JsonFormat(pattern = "ddd/MM/yyyy HH:mm:ss")
	private LocalDateTime data_hora_entrada;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime data_hora_emissao_nota;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_cancelamento;
	@Column(length = 60)
	private String naturezaopercao;
	@Column(length = 10)
	private String modelo;
	@Column(length = 10)
	private String serie;
	@Setter(value = AccessLevel.NONE)
	@Getter(value = AccessLevel.NONE)
	@Digits(integer = 9, fraction = 4)
	@Transient
	private BigDecimal totalProduto;
	@Embedded
	private ImpostoNota impostoNota;
	@Embedded
	private TransporteNotafiscal transporteNotafiscal;
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private StatusEntradaNota statusEntradaNota;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entradaNotafiscal", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemEntradaNota> items_entrada = new ArrayList<>();
	@Column(length = 255)
	private String arquivo_nota;
	@Column(length = 60)
	private String chaveNota;

	public EntradaNotaCabecario() {
		totalProduto = BigDecimal.ZERO;
	}

	public void setTotalProduto(BigDecimal totalProduto) {
		this.totalProduto = totalProduto.setScale(4, RoundingMode.HALF_EVEN);
	}

	@Transient
	private BigDecimal somarTotalnota() {

		for (int i = 0; i < items_entrada.size(); i++) {
			this.totalProduto = totalProduto.add(items_entrada.get(i).getSubtotal());

		}

		return totalProduto;
	}

	public BigDecimal getTotalProduto() {
		return somarTotalnota();
	}

	@Transient
	public LocalDateTime converte(String data) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		OffsetDateTime offsetDateTime = OffsetDateTime.parse(data, inputFormatter);
		data_hora_entrada = offsetDateTime.toLocalDateTime();
		return data_hora_entrada;
	}

}
