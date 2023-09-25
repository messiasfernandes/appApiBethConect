/**
 * 
 */
package br.com.bethpapp.dominio.entidade;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */
@Setter
@Getter
@Entity
public class Fornecedor extends Pessoa {

	
	private static final long serialVersionUID = 1L;

	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "fornecedores", cascade = CascadeType.ALL)
	
	private List<Produto> produtos = new ArrayList<>();

}
