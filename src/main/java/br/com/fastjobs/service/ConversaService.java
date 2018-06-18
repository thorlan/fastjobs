package br.com.fastjobs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.exception.RecursoInexistenteException;
import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.repository.ConversaRepository;
import br.com.fastjobs.repository.filter.ConversaFilter;

@Service
public class ConversaService {

	@Autowired
	private ConversaRepository conversaRepository;

	public List<Conversa> buscarPorUsuario(Long usuarioId) {
	
		List<Conversa> conversasDoUsuario = new ArrayList<>();
		
		List<Long> conversasTeste = this.conversaRepository.buscaPorUsuario(usuarioId);
		for (Long idAchado : conversasTeste) {
			Conversa conversaBuscada = this.conversaRepository.findById(idAchado)
					.orElseThrow(() -> new RecursoInexistenteException("Conversa não encontrada"));
			conversasDoUsuario.add(conversaBuscada);
		}
		return conversasDoUsuario;
	}
	
	public Conversa buscaUmaConversaEspecifica(ConversaFilter conversaFilter) {
		Long conversaId = this.conversaRepository.buscaConversaEntreDoisUsuarios(conversaFilter);
		return this.conversaRepository.findById(conversaId).get();
	}
	
}
