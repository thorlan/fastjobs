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
import br.com.fastjobs.model.Servico;
import br.com.fastjobs.repository.FotoRepository;
import br.com.fastjobs.repository.ServicoRepository;
import br.com.fastjobs.service.FotoService;

@RestController
@RequestMapping("/fotos")
public class FotoResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoRepository fotoRepository;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private ServicoRepository servicoRepository;
	
	@GetMapping
	public List<Foto> buscarTodas(){
		return this.fotoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Foto> buscarPorId(@PathVariable Long id) {
		Foto foto = this.fotoRepository.findById(id).orElseGet(()-> {
			Foto fotoNull = null;
			return fotoNull;
		});
		return foto == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(foto);
	}
	
	@GetMapping("/servico/{id}")
	public List<Foto> buscarPorServico(@PathVariable Long id) {
		Servico servico = this.servicoRepository.findById(id).get();
		return this.fotoRepository.findByServico(servico);
	}
	
	@PostMapping
	public ResponseEntity<Foto> criar(@Valid @RequestBody Foto foto, HttpServletResponse response) {
		Foto fotoSalva = this.fotoService.salvar(foto);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, foto.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fotoSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Foto> atualizar(@PathVariable Long id, @Valid @RequestBody Foto foto) {
		Foto fotoAtualizada = this.fotoService.atualizar(id, foto);
		return ResponseEntity.ok(fotoAtualizada);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		fotoRepository.deleteById(id);
	}

}
