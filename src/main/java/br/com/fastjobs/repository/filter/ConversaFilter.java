package br.com.fastjobs.repository.filter;

public class ConversaFilter {

	private Long remetenteId;
	private Long destinatarioId;
	
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

}
