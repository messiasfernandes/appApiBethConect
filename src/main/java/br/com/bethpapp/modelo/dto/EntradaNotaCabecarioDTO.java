package br.com.bethpapp.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.entidade.ImpostoNota;
import br.com.bethpapp.dominio.entidade.TransporteNotafiscal;
import br.com.bethpapp.dominio.enumerado.StatusEntradaNota;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data

public class EntradaNotaCabecarioDTO {
	private Long id;
	@Column(length = 25)
	private String numerodanota;

	private String naturezaopercao;

	private String modelo;

	private String serie;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data_hora_entrada;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data_hora_emissao_nota;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_cancelamento;
	private BigDecimal totalProduto;
	private ImpostoNota impostoNota;
	private TransporteNotafiscal transporteNotafiscal;
	@Enumerated(EnumType.STRING)
	private StatusEntradaNota statusEntradaNota;
	private BigDecimal pesoBruto;
	private FornecedorDTO fornecedor;
	private String chaveNota;
	private List<ItemEntradaNotaDTO> items_entrada = new ArrayList<>();
	@Column(length = 255)
	private String arquivo_nota;

}
