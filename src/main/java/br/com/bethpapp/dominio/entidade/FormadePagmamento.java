package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FormadePagmamento extends GeradorId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Campo Obrigatório")
	@Column(length = 60, nullable = false, unique = true)
	private String nomeforma;

}
