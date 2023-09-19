package br.com.bethpapp.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.Fornecedor;
import br.com.bethpapp.dominio.service.ServiceFuncoes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ForncedorQueryImpl extends ServiceFuncoes implements ForncedorQuery{
	@PersistenceContext
	EntityManager em;
	@Override
	public Page<Fornecedor> buscar(String paramentro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Fornecedor> criteria = builder.createQuery(Fornecedor.class);
		Root<Fornecedor> root = criteria.from(Fornecedor.class);
		Predicate[] predicates = null;

		predicates = criarRestricoes(paramentro, builder, root);

		root.fetch("cidade", JoinType.LEFT);
	
		// root.fetch("componentes", JoinType.LEFT);
		criteria.select(root);

		criteria.where(predicates);

		criteria.orderBy(builder.asc(root.get("nome")));
		TypedQuery<Fornecedor> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro));
	}

	private Long total(String paramentro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Fornecedor> root = criteria.from(Fornecedor.class);

		criteria.where(criarRestricoes(paramentro, builder, root));

		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Fornecedor> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(String paramentro, CriteriaBuilder builder, Root<Fornecedor> root) {
		List<Predicate> predicates = new ArrayList<>();

		if ((!ehnumero(paramentro) && (qtdecaraceteres(paramentro) > 0))) {

			predicates.add(builder.like(root.get("nome"), paramentro.toUpperCase() + "%")

					

			);

		}

		if ((ehnumero(paramentro)) && (qtdecaraceteres(paramentro) != 9)) {
			System.out.println("pasou aqui");
			Long id = Sonumero(paramentro);
			predicates.add(builder.or(builder.equal(root.get("id"), id)));
		}

		

		return predicates.toArray(new Predicate[predicates.size()]);
	}


}
