package br.com.fastjobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fastjobs.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
