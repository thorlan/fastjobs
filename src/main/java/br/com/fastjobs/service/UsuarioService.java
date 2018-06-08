package br.com.fastjobs.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.exception.RecursoInexistenteException;
import br.com.fastjobs.model.Usuario;
import br.com.fastjobs.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario atualizar(Long id, Usuario usuario) {

		Usuario usuarioSalvo = this.usuarioRepository.findById(id).orElseThrow(
				()-> new RecursoInexistenteException("Usuário inexistente") );

		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");

		return this.usuarioRepository.save(usuarioSalvo);
	}
	
	public Usuario salvar(Usuario usuario) {
		usuario.setAtivo(true);
		return this.usuarioRepository.save(usuario);
	}
	
	public Usuario trocarStatus(Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
				()-> new RecursoInexistenteException("Usuário não encontrado"));
		usuario.setAtivo(!usuario.isAtivo());
		return this.usuarioRepository.save(usuario);
	}
	//TODO: delete apagando tudo, servicos, fotos, geral!
	//TODO: desativar-> Fazer um desativar, para nao perder as coisas em vez de deletar.
	//TODO: TRATAR A EXCEPTION DAQUI E DE SERVICOSERVICE
}
