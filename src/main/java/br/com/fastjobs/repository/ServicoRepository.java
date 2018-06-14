package br.com.fastjobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fastjobs.model.Servico;
import br.com.fastjobs.repository.servico.ServicoRepositoryQuery;

public interface ServicoRepository extends JpaRepository<Servico, Long>, ServicoRepositoryQuery{

}
