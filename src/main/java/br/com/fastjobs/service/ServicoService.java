package br.com.fastjobs.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.exception.RecursoInexistenteException;
import br.com.fastjobs.model.Servico;
import br.com.fastjobs.repository.ServicoRepository;
import br.com.fastjobs.repository.UsuarioRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Servico atualizar(Long id, @Valid Servico servico) {
		
		Servico servicoSalvo = this.servicoRepository.findById(id).orElseThrow(
				()-> new RecursoInexistenteException("Serviço não inexistente"));
		
		BeanUtils.copyProperties(servico, servicoSalvo, "codigo");

		return this.servicoRepository.save(servicoSalvo);
	}
	
	public Servico salvar(Servico servico) {
		
		if (servico.getUsuario().getId() == null || !usuarioRepository.findById(servico.getUsuario().getId()).isPresent()) {
			throw new RecursoInexistenteException("Usuário não encontrado");
		}

		return this.servicoRepository.save(servico);
	}
	
}
