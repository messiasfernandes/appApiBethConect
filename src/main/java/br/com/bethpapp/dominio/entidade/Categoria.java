/**
 * 
 */
package br.com.bethpapp.dominio.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */

@Getter
@Setter
@Entity
public class Categoria extends GeradorId {

	private static final long serialVersionUID = 1L;

	@Setter(value = AccessLevel.NONE)

	@Column(length = 50)
	private String nomecategoria;

	public void setNomecategoria(String nomecategoria) {
		if (nomecategoria != null) {
			this.nomecategoria = nomecategoria.toUpperCase();
		} else {
			this.nomecategoria = nomecategoria;
		}
	}

//	@JsonManagedReference
//	@Fetch(FetchMode.SUBSELECT)
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<SubCategoria> subcategorias = new ArrayList<>();

}
