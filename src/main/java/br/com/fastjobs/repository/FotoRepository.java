package br.com.fastjobs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fastjobs.model.Foto;
import br.com.fastjobs.model.Servico;

public interface FotoRepository extends JpaRepository<Foto, Long>{

	List<Foto> findByServico(Servico servico);
}
