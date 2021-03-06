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

import br.com.fastjobs.event.RecursoCriadoEvent;
import br.com.fastjobs.model.Comentario;
import br.com.fastjobs.model.Foto;
import br.com.fastjobs.repository.ComentarioRepository;
import br.com.fastjobs.repository.FotoRepository;
import br.com.fastjobs.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioResource {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private ComentarioService comentarioService;

	@Autowired
	private FotoRepository fotoRepository;
	//TODO: PASSAR TODO CODIGO QUE TENHA ALGUMA LOGICA PARA O SERVICE. 
	//NOS RESOURCE SO CHAMAMOS OS SERVICES E VOLTAMOS O CAMINHO FELIZ.
	@GetMapping("/{id}")
	public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id) {
		Comentario comentario = this.comentarioRepository.findById(id).orElseGet(()-> {
			return null; //TODO: RETORNAR NULL EM VEZ DE FAZER ISSO	
		});
		return comentario == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(comentario);
	}
	
	@GetMapping("/foto/{id}")
	public List<Comentario> buscarPorFoto(@PathVariable Long id) {
		Foto foto = this.fotoRepository.findById(id).get();
		return this.comentarioRepository.findByFoto(foto);
	}
	
	@PostMapping
	public ResponseEntity<Comentario> criar(@Valid @RequestBody Comentario comentario, HttpServletResponse response) {
		Comentario comentarioSalvo = this.comentarioService.salvar(comentario);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, comentarioSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(comentarioSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @Valid @RequestBody Comentario comentario) {
		Comentario comentarioAtualizado = this.comentarioService.atualizar(id, comentario);
		return ResponseEntity.ok(comentarioAtualizado);
	}
	
	@PutMapping("/foiVisto/{id}")
	public ResponseEntity<Comentario> trocarStatus(@PathVariable Long id) {
		Comentario comentarioAtualizado = this.comentarioService.trocarStatus(id);
		return ResponseEntity.ok(comentarioAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		comentarioRepository.deleteById(id);
	}
	
	
}
