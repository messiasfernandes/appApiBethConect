package br.com.bethpapp.dominio.service;

import org.springframework.util.StringUtils;

public class ServiceFuncoes {
	

	public Long Sonumero(String palavra) {
		String digitos = "";
		char[] letras = palavra.toCharArray();
		for (char letra : letras) {
			if (Character.isDigit(letra)) {
				digitos += letra;
			}
		}
		return Long.valueOf(digitos);
	}

	public Boolean ehnumero(String parametro) {
		boolean isNumeric = false;
		if (StringUtils.hasText(parametro)) {
			isNumeric = parametro.matches("[+-]?\\d*(\\.\\d+)?");
			return isNumeric;
		} else {

			isNumeric = false;
		}

		return isNumeric;

	}

	public Integer qtdecaraceteres(String parametro) {
		Integer numero = parametro.length();

		return numero;

	}

	public String maiuscula(String pnome) {
		 String nome="";
		if (pnome.length() > 0) {
			nome = pnome.toUpperCase();
		}
		return nome;
	}
	
	
}
