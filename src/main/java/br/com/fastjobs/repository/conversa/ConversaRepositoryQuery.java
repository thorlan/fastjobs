package br.com.fastjobs.repository.conversa;

import java.util.List;

import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.repository.filter.ConversaFilter;

public interface ConversaRepositoryQuery {
	
	public Long buscaConversaEntreDoisUsuarios(ConversaFilter conversaFilter);
	
	public List<Conversa> buscaConversaPorUsuario(Long id);

}
