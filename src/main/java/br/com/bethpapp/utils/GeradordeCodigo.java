package br.com.bethpapp.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeradordeCodigo {
	public static String CriarEann13() {
		   LocalDateTime datahora= LocalDateTime.now();
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		   String formattedDateTime = datahora.format(formatter);
	       String stringConcatenada ="789"+formattedDateTime.substring(5,14);
	 
	       System.out.println(formattedDateTime);
	      System.out.println(stringConcatenada);
	      String ean13 = CalcularDigitoEan.calcularEAN13(stringConcatenada);
		   CodigoBarraEAN codigoBarra = new CodigoBarraEAN(ean13);
			System.out.println("Codigo de barra: " + codigoBarra.validar(codigoBarra));
			System.out.println("Numero do codigo de barras: " + codigoBarra.getCodigoBarra());
			return codigoBarra.getCodigoBarra();
	}
	public static String GerarCodigoFabricante() {
		 LocalDateTime datahora= LocalDateTime.now();
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		   String dataFormatada = datahora.format(formatter);
		String codigofabricante=dataFormatada.substring(8,14 );
		System.out.println(codigofabricante);
		return codigofabricante;
	}
}
