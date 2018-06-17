package br.com.fastjobs.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fastjobs.event.RecursoCriadoEvent;
import br.com.fastjobs.model.Mensagem;
import br.com.fastjobs.repository.MensagemRepository;
import br.com.fastjobs.service.MensagemService;

@RestController
@RequestMapping("/mensagens")
public class MensagemResource {

	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private MensagemService mensagemService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Mensagem> criar(@Valid @RequestBody Mensagem mensagem, HttpServletResponse response) {
				
		Mensagem mensagemSalva = this.mensagemService.salvar(mensagem);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, mensagemSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(mensagemSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mensagem> buscarPorId(@PathVariable Long id) {
		Mensagem mensagem = this.mensagemRepository.findById(id).orElseGet(()-> {
			Mensagem mensagemNull = null;
			return mensagemNull;
		});
		return mensagem == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(mensagem);
	}
	
	//TODO: DELETE 
}
