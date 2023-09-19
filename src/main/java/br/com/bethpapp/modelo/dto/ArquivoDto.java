package br.com.bethpapp.modelo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArquivoDto {
	//extends RepresentationModel< ArquivoDto>
	private String nomeArquivo;

	private String descricao;

	private String contentType;

	private String url;
	private Long tamanho;

}
