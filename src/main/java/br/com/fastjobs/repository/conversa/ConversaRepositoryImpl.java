package br.com.fastjobs.repository.conversa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.model.Conversa_;
import br.com.fastjobs.model.Mensagem;
import br.com.fastjobs.model.Mensagem_;
import br.com.fastjobs.repository.filter.ConversaFilter;

public class ConversaRepositoryImpl implements ConversaRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Conversa> buscaConversaPorUsuario(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Conversa> criteriaConversa = builder.createQuery(Conversa.class);

		Root<Conversa> conversaRoot = criteriaConversa.from(Conversa.class);
		Root<Mensagem> mensagemRoot = criteriaConversa.from(Mensagem.class);

		Predicate conversaIdIgualMensagemId = builder.equal(conversaRoot.get(Conversa_.id),
				mensagemRoot.get(Mensagem_.conversa));
		Predicate destinatarioIgualAoId = builder.equal(mensagemRoot.get(Mensagem_.destinatarioId), id);
		Predicate remetenteIgualAoId = builder.equal(mensagemRoot.get(Mensagem_.destinatarioId), id);
		Predicate orCondition = builder.or(destinatarioIgualAoId, remetenteIgualAoId);

		TypedQuery<Conversa> testeQuery = entityManager.createQuery(criteriaConversa.select(conversaRoot)
				.where(builder.and(conversaIdIgualMensagemId), orCondition).distinct(true));

		return testeQuery.getResultList();
	}

	@Override
	public Conversa buscaConversaEntreDoisUsuariosTeste(ConversaFilter conversaFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Conversa> criteriaConversa = builder.createQuery(Conversa.class);

		Root<Conversa> conversaRoot = criteriaConversa.from(Conversa.class);
		Root<Mensagem> mensagemRoot = criteriaConversa.from(Mensagem.class);

		Predicate conversaIdIgualMensagemId = builder.equal(conversaRoot.get(Conversa_.id),
				mensagemRoot.get(Mensagem_.conversa));
		
		//+ "(( m.remetenteId = :remetenteId and m.destinatarioId = :destinatarioId)\n" 
		Predicate destinatarioIgualAoId = builder.equal(mensagemRoot.get(Mensagem_.destinatarioId), conversaFilter.getDestinatarioId());
		Predicate remetenteIgualAoId = builder.equal(mensagemRoot.get(Mensagem_.remetenteId), conversaFilter.getRemetenteId());
		Predicate primeiraCondicao = builder.and(destinatarioIgualAoId, remetenteIgualAoId);
		//or
		// remetenteId= destinatarioId and destinatarioId = remetenteId
		Predicate destinatarioIgualAoIdInverso = builder.equal(mensagemRoot.get(Mensagem_.destinatarioId),conversaFilter.getRemetenteId() );
		Predicate remetenteIgualAoIdInverso = builder.equal(mensagemRoot.get(Mensagem_.remetenteId), conversaFilter.getDestinatarioId());
		Predicate segundaCondicao = builder.and(destinatarioIgualAoIdInverso, remetenteIgualAoIdInverso);
		
		Predicate orCondition = builder.or(primeiraCondicao, segundaCondicao);
		
		TypedQuery<Conversa> testeQuery = entityManager.createQuery(criteriaConversa.select(conversaRoot)
				.where(builder.and(conversaIdIgualMensagemId), orCondition).distinct(true));

		return testeQuery.getSingleResult();
	}

}
