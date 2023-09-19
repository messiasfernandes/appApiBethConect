package br.com.bethpapp.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.com.bethpapp.dominio.entidade.SubCategoria;
import jakarta.persistence.criteria.Predicate;

public class SubCategoriaSpec {
	private List<Predicate> predicates = new ArrayList<>();

	public Specification<SubCategoria> buscaid(Long codigo) {

		return (root, query, builder) -> {
			if (codigo != null) {
				if (codigo != null) {

					predicates.add(builder.equal(root.get("id"), codigo));
				}

			}
			return builder.and(predicates.toArray(new Predicate[0]));
		};

	}

	public Specification<SubCategoria> buscar(String nome) {
System.out.println(nome);
		return (root, query, builder) -> {

			return builder.like(root.get("nomeSubCategoria"), nome + "%");

		};

	}

	public Specification<SubCategoria> buscasubCategoriar(String nome) {

		return (root, query, builder) -> {
			if (StringUtils.hasText(nome)) {
				System.out.println("passou aqui");
				predicates.add(builder.like(root.get("nomeSubCategoria"), nome + "%"));
			}
			return builder.and(predicates.toArray(new Predicate[0]));

		};
	}


}
