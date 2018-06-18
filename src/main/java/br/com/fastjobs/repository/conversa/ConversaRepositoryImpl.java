package br.com.fastjobs.repository.conversa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.repository.filter.ConversaFilter;

public class ConversaRepositoryImpl implements ConversaRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	//implementar para buscar a conversa entre X e Y. Onde X = remetente ou destinatario e y = remetente ou destinatario
	@Override
	public Conversa filtrar(ConversaFilter mensagemFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Long> buscaPorUsuario(Long id) {
		
		TypedQuery<Long> query = entityManager.createQuery("select distinct c.id from Conversa c, "
				+ "Mensagem m where (c.id = m.conversa) "
				+ "and (( m.remetenteId = :id) or (m.destinatarioId = :id))" 
				,Long.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	

	/*@Override
	// select distinct conversa_id from mensagem where remetente_id =2 or destinatario_id=2;
	public List<Mensagem> buscaPorUsuario(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mensagem> criteria = builder.createQuery(Mensagem.class);
		Root<Mensagem> root = criteria.from(Mensagem.class);
		
		//Adição das restrições.
		Predicate[] predicates = criarRestrições(id, builder, root);
	
		criteria.where(predicates).distinct(true);
		
		TypedQuery<Mensagem> query = entityManager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] criarRestrições(Long id, CriteriaBuilder builder, Root<Mensagem> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.or(builder.equal(root.get(Mensagem_.destinatarioId), id),
						builder.equal(root.get(Mensagem_.remetenteId), id)));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}*/
}
