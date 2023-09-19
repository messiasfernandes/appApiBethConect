package br.com.bethpapp.modelo.input;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class SubCategoriaInput {
	private Long id;
	@Setter(value = AccessLevel.NONE)
	private String nomeSubCategoria;
	
	private CategoriaInput categoria;

	public void setNomeSubCategoria(String nomeSubCategoria) {
		if (nomeSubCategoria != null) {
			this.nomeSubCategoria = nomeSubCategoria.toUpperCase();
		} else {
			this.nomeSubCategoria = nomeSubCategoria;
		}

	}
	
	
	
}
