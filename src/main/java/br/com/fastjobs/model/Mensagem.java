package br.com.fastjobs.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name = "mensagem")
@JsonRootName("mensagem")
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String mensagem;
	
	@NotNull
	private LocalDateTime diaEHora;
	
	@ManyToOne
	private Conversa conversa;
	
	@OneToOne
	private Usuario destinatario;
	
	public boolean foiVista;

	public boolean isFoiVista() {
		return foiVista;
	}

	public void setFoiVista(boolean foiVista) {
		this.foiVista = foiVista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDiaEHora() {
		return diaEHora;
	}

	public void setDiaEHora(LocalDateTime diaEHora) {
		this.diaEHora = diaEHora;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	
	
	
}
