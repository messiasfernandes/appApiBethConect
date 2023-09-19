package br.com.bethpapp.dominio.entidade;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */
@Getter
@Setter
@Embeddable
public class Atributo {

	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String tipo;
	@Setter(value = AccessLevel.NONE)

	@Column(length = 60)
	private String valor;

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}

	public void setValor(String valor) {
		this.valor = valor.toUpperCase();
	}

}
