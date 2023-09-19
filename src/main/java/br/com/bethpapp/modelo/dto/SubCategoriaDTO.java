package br.com.bethpapp.modelo.dto;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class SubCategoriaDTO {
	private Long id;
	
	private String nomeSubCategoria;

	private CategoriaDTO categoria;



}
