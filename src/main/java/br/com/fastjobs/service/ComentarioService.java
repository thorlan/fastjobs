package br.com.fastjobs.service;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.exception.RecursoInexistenteException;
import br.com.fastjobs.model.Comentario;
import br.com.fastjobs.model.Foto;
import br.com.fastjobs.model.Usuario;
import br.com.fastjobs.repository.ComentarioRepository;
import br.com.fastjobs.repository.FotoRepository;
import br.com.fastjobs.repository.UsuarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private FotoRepository fotoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Comentario salvar(Comentario novoComentario) {
		
		Long fotoId = novoComentario.getFoto().getId();
		Long usuarioId = novoComentario.getUsuario().getId();
	
		Usuario usuario = this.usuarioRepository.findById(usuarioId).orElseThrow(
				()-> new RecursoInexistenteException("Usuário não encontrado"));
		
		Foto foto = this.fotoRepository.findById(fotoId).orElseThrow(
				()-> new RecursoInexistenteException("Foto não encontrada"));
	
		LocalDateTime dataEHora = LocalDateTime.now();
		novoComentario.setDateTime(dataEHora);
		
		return this.comentarioRepository.save(novoComentario);
	}

	public Comentario atualizar(Long id, @Valid Comentario comentario) {
		Comentario comentarioSalvo = this.comentarioRepository.findById(id).orElseThrow(
				()-> new RecursoInexistenteException("Comentário não existe"));
		
		BeanUtils.copyProperties(comentario, comentarioSalvo, "codigo");

		return this.comentarioRepository.save(comentarioSalvo);
	}
}
