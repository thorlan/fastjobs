package br.com.fastjobs.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fastjobs.exception.RecursoInexistenteException;
import br.com.fastjobs.model.Foto;
import br.com.fastjobs.repository.FotoRepository;
import br.com.fastjobs.repository.ServicoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public Foto salvar(Foto foto) {
		
		if (foto.getServico().getId() == null || !servicoRepository.findById(foto.getServico().getId()).isPresent()) {
			throw new RecursoInexistenteException("Serviço não encontrado");
		}
		return this.fotoRepository.save(foto);
	}

	public Foto atualizar(Long id, @Valid Foto foto) {
		Foto fotoSalva = this.fotoRepository.findById(id).orElseThrow(
				()-> new RecursoInexistenteException("Foto não existe"));
		
		BeanUtils.copyProperties(foto, fotoSalva, "codigo");

		return this.fotoRepository.save(fotoSalva);
	}
}
