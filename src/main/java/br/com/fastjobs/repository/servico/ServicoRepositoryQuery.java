package br.com.fastjobs.repository.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fastjobs.model.Servico;
import br.com.fastjobs.repository.filter.ServicoFilter;

public interface ServicoRepositoryQuery {
	
	public Page<Servico> filtrar(ServicoFilter servicoFilter, Pageable pageble);
}
