package br.com.bethpapp.modelo.dto;

import lombok.Data;

@Data
public class FornecedorDTO {
	

	private Long id;
	private String nome;


	private String cpfouCnpj;
	private CidadeDTO cidade;

}
