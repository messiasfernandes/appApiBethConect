package br.com.bethpapp.modelo.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInput {
	private Long id;
	@NotEmpty
	@NotNull
	private String nomecategoria;
}