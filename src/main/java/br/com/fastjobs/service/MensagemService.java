package br.com.fastjobs.service;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.exception.RecursoInexistenteException;
import br.com.fastjobs.model.Mensagem;
import br.com.fastjobs.repository.MensagemRepository;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemRepository;

	public Mensagem salvar(@Valid Mensagem mensagem) {
		mensagem.setFoiVista(false);
		mensagem.setDiaEHora(LocalDateTime.now());

		Mensagem mensagemSalva = this.mensagemRepository.save(mensagem);
		return mensagemSalva;
	}

	public Mensagem foiVista(Long id) {
		Mensagem mensagem = this.mensagemRepository.findById(id)
				.orElseThrow(() -> new RecursoInexistenteException("Mensagem não encontrada"));
		mensagem.setFoiVista(true);
		return this.mensagemRepository.save(mensagem);
	}

}
