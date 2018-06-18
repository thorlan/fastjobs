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
	
	//TODO: PASSAR PARA CRITERIA
	public List<Long> buscaPorUsuario(Long id) {
		
		TypedQuery<Long> query = entityManager.createQuery("select distinct c.id from Conversa c, "
				+ "Mensagem m where (c.id = m.conversa) "
				+ "and (( m.remetenteId = :id) or (m.destinatarioId = :id))" 
				,Long.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}

	//TODO: PASSAR PARA CRITERIA
	@Override
	public Long buscaConversaEntreDoisUsuarios(ConversaFilter conversaFilter) {
		
		TypedQuery<Long> query = entityManager.createQuery("select distinct c.id from Conversa c, Mensagem m "
				+ "where (c.id = m.conversa) "
					+ "and "
				+ "(( m.remetenteId = :remetenteId and m.destinatarioId = :destinatarioId)\n" + 
					"or "
				+ "(m.remetenteId = :destinatarioId and m.destinatarioId = :remetenteId))" 
				,Long.class);
		query.setParameter("destinatarioId", conversaFilter.getDestinatarioId());
		query.setParameter("remetenteId", conversaFilter.getRemetenteId());
		
		return query.getSingleResult();
	}
	
}
