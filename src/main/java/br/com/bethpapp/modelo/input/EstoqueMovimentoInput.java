package br.com.bethpapp.modelo.input;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bethpapp.dominio.enumerado.TipoMovimentacao;
import lombok.Data;
@Data
public class EstoqueMovimentoInput {
	
	private TipoMovimentacao tipoMovimentacao;

	@DateTimeFormat(pattern = " dd/MM/yyyy ")
	@JsonFormat(pattern = "dd/MM/yyyy ")
	private LocalDate datamovimento;

    private ProdutoInput produto;
    private Integer qtde;
	private Integer saldoanterior;

}
