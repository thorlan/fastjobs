package br.com.fastjobs.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fastjobs.event.RecursoCriadoEvent;
import br.com.fastjobs.model.Servico;
import br.com.fastjobs.repository.ServicoRepository;
import br.com.fastjobs.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {
	
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Servico> buscarTodos() {
		List<Servico> servicos = this.servicoRepository.findAll();
		return servicos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
		Servico servico = this.servicoRepository.findById(id).orElseGet(()-> {
			Servico servicoNull = null;
			return servicoNull;
		});
		return servico == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(servico);
	}
	
	@PostMapping
	public ResponseEntity<Servico> criar(@Valid @RequestBody Servico servico, HttpServletResponse response) {
		Servico servicoSalvo = this.servicoService.salvar(servico);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, servico.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Servico> atualizar(@PathVariable Long id, @Valid @RequestBody Servico servico) {
		Servico servicoAtualizado = this.servicoService.atualizar(id, servico);
		return ResponseEntity.ok(servicoAtualizado);
	}

}
