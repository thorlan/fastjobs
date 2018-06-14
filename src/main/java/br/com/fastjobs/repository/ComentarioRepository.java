package br.com.fastjobs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fastjobs.model.Comentario;
import br.com.fastjobs.model.Foto;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	List<Comentario> findByFoto(Foto foto);
	
	//List<Comentario> findByFoiVistoAndService(boolean foiVisto); 
	//Buscar os comentarios para um determinado usuario que nao foram vistos ainda.
	//Para poder gerar uma notificacao 
}
