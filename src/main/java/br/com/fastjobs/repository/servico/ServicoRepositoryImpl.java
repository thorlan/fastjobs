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

import org.springframework.util.StringUtils;

import br.com.fastjobs.model.Servico;
import br.com.fastjobs.model.Servico_;
import br.com.fastjobs.repository.filter.ServicoFilter;

public class ServicoRepositoryImpl implements ServicoRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Servico> filtrar(ServicoFilter servicoFilter) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Servico> criteria = builder.createQuery(Servico.class);
		Root<Servico> root = criteria.from(Servico.class);
		
		//Adição das restrições.
		Predicate[] predicates = criarRestrições(servicoFilter, builder, root);
	
		criteria.where(predicates);
		
		TypedQuery<Servico> query = entityManager.createQuery(criteria);
		return query.getResultList();
		
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

}
