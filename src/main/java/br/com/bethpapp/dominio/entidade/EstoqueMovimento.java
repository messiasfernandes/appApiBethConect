package br.com.bethpapp.dominio.entidade;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.enumerado.TipoMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EstoqueMovimento extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	@Column(columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = " dd/MM/yyyy HH:mm ")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime datamovimento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
    private Produto produto;
	private Integer qtde;
	private Integer saldoanterior;

}
