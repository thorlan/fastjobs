package br.com.fastjobs.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.fastjobs.event.RecursoCriadoEvent;
import br.com.fastjobs.model.Usuario;
import br.com.fastjobs.repository.UsuarioRepository;
import br.com.fastjobs.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = this.usuarioService.salvar(usuario);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuario.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@GetMapping
	public List<Usuario> buscarTodos() {
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		return usuarios;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).orElseGet(()-> {
			Usuario usuarioNull = null;
			return usuarioNull;
		});
		return usuario == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioAtualizado = this.usuarioService.atualizar(id, usuario);
		return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@PutMapping("/ativo/{id}")
	public ResponseEntity<Usuario> trocarStatus(@PathVariable Long id) {
		Usuario usuarioAtualizado = this.usuarioService.trocarStatus(id);
		return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}

}
