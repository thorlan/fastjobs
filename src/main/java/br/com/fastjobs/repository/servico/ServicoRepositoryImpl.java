package br.com.fastjobs.repository.servico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.fastjobs.model.Servico;
import br.com.fastjobs.model.Servico_;
import br.com.fastjobs.repository.filter.ServicoFilter;

public class ServicoRepositoryImpl implements ServicoRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Servico> filtrar(ServicoFilter servicoFilter, Pageable pageable) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Servico> criteria = builder.createQuery(Servico.class);
		Root<Servico> root = criteria.from(Servico.class);
		
		//Adição das restrições.
		Predicate[] predicates = criarRestrições(servicoFilter, builder, root);
	
		criteria.where(predicates);
		
		TypedQuery<Servico> query = entityManager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(servicoFilter));
		
	}

	private Predicate[] criarRestrições(ServicoFilter servicoFilter, CriteriaBuilder builder, Root<Servico> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(servicoFilter.getDescricao())) {
			
			predicates.add(builder.like(
					builder.lower(root.get(Servico_.descricao)), "%" + servicoFilter.getDescricao().toLowerCase() + "%"));
			
		}
		if (!StringUtils.isEmpty(servicoFilter.getNome())) {
			
			predicates.add(builder.like(
					builder.lower(root.get(Servico_.nome)), "%" + servicoFilter.getNome().toLowerCase() + "%"));
			
		}
		if (servicoFilter.getUsuario() != null) {
			predicates.add(builder.equal(root.get(Servico_.usuario), servicoFilter.getUsuario().getId()));
		}

		if (servicoFilter.getTipoServico() != null) {
			predicates.add(builder.equal(root.get(Servico_.tipoServico), servicoFilter.getTipoServico()));			
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Servico> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}

	private Long total(ServicoFilter servicoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Servico> root = criteria.from(Servico.class);
		
		Predicate[] predicates = criarRestrições(servicoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return entityManager.createQuery(criteria).getSingleResult();
		
	}
	
	
}
