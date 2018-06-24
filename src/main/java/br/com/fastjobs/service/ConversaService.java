package br.com.fastjobs.service;

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

	public List<Conversa> buscaPorUsuario(Long id){
		return this.conversaRepository.buscaConversaPorUsuario(id);
	}

	public Conversa buscaUmaConversaEspecifica(ConversaFilter conversaFilter) {
		return this.conversaRepository.buscaConversaEntreDoisUsuariosTeste(conversaFilter);
	}

}
