package br.com.bethpapp.dominio.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Estoque extends GeradorId {

	private static final long serialVersionUID = 1L;
//  @JsonIgnore
	// @JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)

	private Produto produto;

	private Integer quantidade;

	public Estoque() {

		this.quantidade = 0;
	}

}
