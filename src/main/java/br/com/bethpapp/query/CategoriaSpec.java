package br.com.bethpapp.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.com.bethpapp.dominio.entidade.Categoria;
import jakarta.persistence.criteria.Predicate;

public class CategoriaSpec {
	private List<Predicate> predicates = new ArrayList<>();
	public Specification<Categoria> buscaid(Long codigo) {

		return (root, query, builder) -> {
			if (codigo != null) {
				if (codigo != null) {

					predicates.add(builder.equal(root.get("id"), codigo));
				}

			}
			return builder.and(predicates.toArray(new Predicate[0]));
		};

	}
	public Specification<Categoria> buscar(String nome) {
	       
		return (root, query, builder) -> {

			return builder.like(root.get("nomecategoria"),  nome + "%");

		};

	}
	
	public Specification<Categoria> buscaCategorias(String nome) {

		return (root, query, builder) -> {
			if (StringUtils.hasText(nome)) {
		System.out.println("passou aqui");
				predicates.add(builder.like(root.get("nomecategoria"), nome + "%"));
			}
			return builder.and(predicates.toArray(new Predicate[0]));

		};
	}

}
