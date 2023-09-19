package br.com.bethpapp.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.Comanda;
import br.com.bethpapp.dominio.service.ServiceFuncoes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ComandaQueryImpl extends ServiceFuncoes implements ComandaQuery{
	@PersistenceContext
	EntityManager em;

	@Override
	public Page<Comanda> buscar(String paramentro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Comanda> criteria = builder.createQuery(Comanda.class);
		Root<Comanda> root = criteria.from(Comanda.class);
		Predicate[] predicates = null;

		predicates = criarRestricoes(paramentro, builder, root);

		root.fetch("itemsdaComanda", JoinType.LEFT).fetch("produto", JoinType.LEFT);
	
		
		criteria.select(root);

		criteria.where(predicates);

	
		TypedQuery<Comanda> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro));
	}

	private Long total(String paramentro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Comanda> root = criteria.from(Comanda.class);

		criteria.where(criarRestricoes(paramentro, builder, root));

		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Comanda> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(String paramentro, CriteriaBuilder builder, Root<Comanda> root) {
		List<Predicate> predicates = new ArrayList<>();



		if ((ehnumero(paramentro)) ) {
			System.out.println("pasou aqui");
			Long nummesa = Sonumero(paramentro);
			predicates.add(builder.or(builder.equal(root.get("mesa").get("numerodaMesa"), nummesa)));
		}

		if ((ehnumero(paramentro)) && (qtdecaraceteres(paramentro) == 13)) {
			predicates.add(builder.like(root.get("codigoEan13"), paramentro + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
