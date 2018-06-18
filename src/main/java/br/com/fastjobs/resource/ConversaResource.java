package br.com.fastjobs.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fastjobs.event.RecursoCriadoEvent;
import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.repository.ConversaRepository;
import br.com.fastjobs.service.ConversaService;

@RestController
@RequestMapping("/conversas")
public class ConversaResource {

	@Autowired
	private ConversaRepository conversaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private ConversaService conversaService;
	
	@PostMapping
	public ResponseEntity<Conversa> criar(HttpServletResponse response) {
		
		Conversa conversa = new Conversa();
		Conversa conversaSalva = this.conversaRepository.save(conversa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, conversaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(conversaSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conversa> buscarPorId(@PathVariable Long id) {
		Conversa conversa = this.conversaRepository.findById(id).orElseGet(()-> {
			Conversa conversaNull = null;
			return conversaNull;
		});
		return conversa == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(conversa);
	}
	
	//TODO: BUSCAR CONVERSA DE UM UNICO USUARIO (BUSCAR DISTINC EM MSG ONDE USER1 OU USER2 == USER_ID
	@GetMapping("/usuario/{id}")
	public List<Conversa> buscarPorUsuario(@PathVariable Long id) {
		List<Conversa> conversas = this.conversaService.buscarPorUsuario(id);
		return conversas;
	}
	
	
	//TODO: BUSCAR UMA CONVERSA ENTRE DOIS USUARIOS (
				//BUSCAR EM MSG ONDE  ((USER1 OU USER2) == USUARIO1) 
				//AND
				// (USER1 OU USER2) == USUARIO2)
}
