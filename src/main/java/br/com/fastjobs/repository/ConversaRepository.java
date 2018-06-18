package br.com.fastjobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.repository.conversa.ConversaRepositoryQuery;

public interface ConversaRepository extends JpaRepository<Conversa, Long>, ConversaRepositoryQuery {

}
