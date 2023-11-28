package br.com.bethpapp.utils;

public class ToLowerCase {

	
	public static  String normalizarString( final String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return nome;  // ou lançar uma exceção, dependendo dos requisitos
        }

        String[] palavras = nome.trim().split("\\s+");
        StringBuilder nomeNormalizado = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                if (nomeNormalizado.length() > 0) {
                    nomeNormalizado.append(" ");
                }
                nomeNormalizado.append(palavra.substring(0, 1).toUpperCase())
                               .append(palavra.substring(1).toLowerCase());
            }
        }

        return nomeNormalizado.toString();
    }
}
