package br.com.bethpapp.modelo.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.enumerado.TipoMovimentacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EstoqueProdutoDTO {
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;
	private Integer qtde;
	private Integer saldoanterior;
	private ProdutoLigtDTO produto;

}
