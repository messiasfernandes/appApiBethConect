package br.com.bethpapp.modelo.dto;

import lombok.Data;

@Data
public class CidadeDTO {
    private Long cod_municipio;
	private String nome;
	private EstadoDTO estado;
}
