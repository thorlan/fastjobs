package br.com.fastjobs.repository.servico;

import java.util.List;

import br.com.fastjobs.model.Servico;
import br.com.fastjobs.repository.filter.ServicoFilter;

public interface ServicoRepositoryQuery {
	
	public List<Servico> filtrar(ServicoFilter servicoFilter);
}
