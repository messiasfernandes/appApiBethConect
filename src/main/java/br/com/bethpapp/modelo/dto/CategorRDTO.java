package br.com.bethpapp.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategorRDTO( @NotNull Long id,
 @NotEmpty @NotBlank String nomecategoria) {

}
