package br.com.bethpapp.dominio.entidade;

import br.com.bethpapp.utils.ToLowerCase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Unidade extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Setter(value = AccessLevel.NONE)
	@NotNull
	@Column(length = 2)
	private String abreviatura;
	@Setter(value = AccessLevel.NONE)
	@NotNull
	@Column(length = 30)
	private String descricao;

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = ToLowerCase.normalizarString(abreviatura);
	}

	public void setDescricao(String descricao) {
		this.descricao = ToLowerCase.normalizarString(descricao);
	}
}
