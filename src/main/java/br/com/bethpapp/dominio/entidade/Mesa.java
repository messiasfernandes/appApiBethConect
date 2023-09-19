package br.com.bethpapp.dominio.entidade;

import br.com.bethpapp.dominio.enumerado.Localizacao;
import br.com.bethpapp.dominio.enumerado.StatusMesa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity

public class Mesa extends GeradorId {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Column
	private Integer numerodaMesa;
	@NotNull
	private Integer capacidade;
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private Localizacao loclizacao;
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private StatusMesa statusMesa;
}
