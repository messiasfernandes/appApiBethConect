package br.com.bethpapp.dominio.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class SubCategoria extends GeradorId {

	
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Column(length = 60 , nullable = false)
	private String nomeSubCategoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Categoria categoria;

}
