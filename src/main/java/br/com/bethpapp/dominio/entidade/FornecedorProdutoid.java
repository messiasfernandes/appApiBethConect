package br.com.bethpapp.dominio.entidade;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FornecedorProdutoid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
	private Long produtoid;
	@EqualsAndHashCode.Include
	@Column(name = "fornecedor_id")
	private Long fornecedoid;

}
