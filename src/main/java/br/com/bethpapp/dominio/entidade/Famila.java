package br.com.bethpapp.dominio.entidade;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
public class Famila extends GeradorId {

	
	private static final long serialVersionUID = 1L;
	@Setter(value = AccessLevel.NONE)
	@Nonnull
	@Column(nullable = false, unique = true)
	private String descricao;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private SubCategoria subCategoria;
    public void setDescricao(String descricao) {
	this.descricao = descricao.toUpperCase();
}
}
