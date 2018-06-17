package br.com.fastjobs.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@Table(name = "mensagem")
@JsonRootName("mensagem")
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long remetenteId;
	
	@NotNull
	private Long destinatarioId;
	
	@ManyToOne
	private Conversa conversa;
	
	@NotNull
	private String mensagem;
	
	private LocalDateTime diaEHora;
	
	public boolean foiVista;

	public Long getRemetenteId() {
		return remetenteId;
	}

	public void setRemetenteId(Long remetenteId) {
		this.remetenteId = remetenteId;
	}

	public Long getDestinatarioId() {
		return destinatarioId;
	}

	public void setDestinatarioId(Long destinatarioId) {
		this.destinatarioId = destinatarioId;
	}
	
	@JsonIgnore
	public Conversa getConversa() {
		return conversa;
	}
	@JsonProperty
	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

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
}
