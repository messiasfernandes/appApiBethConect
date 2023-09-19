package br.com.bethpapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcularDigitoEan {
	public static String calcularEAN13(String input) {
		if (input.length() != 12) {
			throw new IllegalArgumentException("O código de entrada deve ter 12 dígitos.");
		}

		// Passo 1: Somar os dígitos nas posições pares (começando a contar do 1)
		int somaPares = 0;
		int somaImpares = 0;
		for (int i = 0; i < 12; i++) {
			int digito = Integer.parseInt(String.valueOf(input.charAt(i)));
			if ((i + 1) % 2 == 0) {
				somaPares += digito;
			} else {
				somaImpares += digito;
			}
		}

		// Passo 2: Multiplicar a soma dos dígitos nas posições pares por 3
		somaPares *= 3;

		// Passo 3: Somar as somas das posições pares e ímpares
		int somaTotal = somaPares + somaImpares;

		// Passo 4: Calcular o próximo número inteiro múltiplo de 10
		int proximoMultiploDe10 = (somaTotal + 9) / 10 * 10;

		// Passo 5: Subtrair a soma total do próximo múltiplo de 10
		int digitoVerificador = proximoMultiploDe10 - somaTotal;

		// Passo 6: Se o digitoVerificador for 10, o 13º dígito será 0
		if (digitoVerificador == 10) {
			digitoVerificador = 0;
		}

		// Passo 7: Retornar o código EAN-13 completo
		return input + digitoVerificador;
	}

	public static String extraireFormatarnumero(String valor) {
		Pattern pattern = Pattern.compile("\\d+"); // Isso corresponderá a um ou mais dígitos
		Matcher matcher = pattern.matcher(valor);
		String numeroEncontrado = "";
		if (matcher.find()) {
			numeroEncontrado = matcher.group();
			// Verifique o comprimento do número
			if (numeroEncontrado.length() == 3) {
				numeroEncontrado = "0" + numeroEncontrado; // Adiciona um zero à esquerda
			} else if (numeroEncontrado.length() == 2) {
				numeroEncontrado = numeroEncontrado + "0"; // Adiciona um zero à direita
			}
			// Se o número já tiver 4 dígitos, não faz nada

			System.out.println("Número formatado: " + numeroEncontrado);
		} else {
			System.out.println("Nenhum número encontrado na string.");
		}
		return numeroEncontrado;
	}

	public static String extraireFormatar(String valor) {
		Pattern pattern = Pattern.compile("\\d+"); // Isso corresponderá a um ou mais dígitos
		Matcher matcher = pattern.matcher(valor);
		String numeroEncontrado = "";
		String num="";
		  while (matcher.find()) {
			  num  = matcher.group();
			  numeroEncontrado +=num;
	            System.out.println("Número encontrado: " + numeroEncontrado);
	        }
			int comprimento = numeroEncontrado.length();
           System.out.println(comprimento);
			if (comprimento == 2) {
				numeroEncontrado = "0" + numeroEncontrado;
			}
			if (comprimento == 3) {
				numeroEncontrado = "0" + numeroEncontrado;
			}
			if (comprimento > 3) {
				System.out.println(numeroEncontrado);
				var numeromaior= numeroEncontrado.substring(0, 4);
				numeroEncontrado = numeromaior;
				System.out.println("Número Maior: " + numeromaior);
			}
			System.out.println("Número formatado: " + numeroEncontrado);
		
		//	System.out.println("Nenhum número encontrado na string.");
		
			
		
		return numeroEncontrado;
	}
	
	public String extractAndFormatNumber(String valor) {
		   Pattern pattern = Pattern.compile("\\d+"); // Isso corresponderá a um ou mais dígitos
	        Matcher matcher = pattern.matcher(valor);

	        StringBuilder numeroEncontrado = new StringBuilder();
	        int comprimento= numeroEncontrado.toString().length();
	        
	        
	        return numeroEncontrado.toString();
	}
}
