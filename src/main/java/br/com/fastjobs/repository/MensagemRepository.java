package br.com.fastjobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fastjobs.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

	
}
