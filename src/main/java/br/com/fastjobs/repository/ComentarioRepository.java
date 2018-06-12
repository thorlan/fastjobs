package br.com.fastjobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fastjobs.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
