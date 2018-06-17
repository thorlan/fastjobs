package br.com.fastjobs.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.model.Conversa;
import br.com.fastjobs.model.Mensagem;
import br.com.fastjobs.repository.ConversaRepository;
import br.com.fastjobs.repository.MensagemRepository;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Autowired
	private ConversaRepository conversaRepository;

	public Mensagem salvar(@Valid Mensagem mensagem) {
		//TODO: ALTERAR ISSO TUDO AQUI. PROVISORIO ISSO!
		mensagem.setFoiVista(false);
		mensagem.setDiaEHora(LocalDateTime.now());
	
		Conversa conversa = this.conversaRepository.findById(mensagem.getConversa().getId()).get();
		List<Mensagem> mensagens = conversa.getMensagens();
		
		Mensagem mensagemSalva = this.mensagemRepository.save(mensagem);
		mensagens.add(mensagemSalva);
		conversa.setMensagens(mensagens);
		this.conversaRepository.save(conversa);
	
		
		return mensagemSalva;
	}
	
	
}
