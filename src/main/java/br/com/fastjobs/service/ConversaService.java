package br.com.fastjobs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.exception.RecursoInexistenteException;
import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.repository.ConversaRepository;

@Service
public class ConversaService {

	@Autowired
	private ConversaRepository conversaRepository;

	public List<Conversa> buscarPorUsuario(Long id) {
	
		List<Conversa> conversasDoUsuario = new ArrayList<>();
		
		List<Long> conversasTeste = this.conversaRepository.buscaPorUsuario(id);
		for (Long idAchado : conversasTeste) {
			Conversa conversaBuscada = this.conversaRepository.findById(idAchado)
					.orElseThrow(() -> new RecursoInexistenteException("Conversa não encontrada"));
			conversasDoUsuario.add(conversaBuscada);
		}
		return conversasDoUsuario;
	}

}
