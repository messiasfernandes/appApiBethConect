package br.com.bethpapp.dominio.entidade;

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
public class ProdutoFornecedorId {
	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
	private Long produto;
	@EqualsAndHashCode.Include
	@Column(name = "fornecedor_id")
	private Long fornecedor;
}
