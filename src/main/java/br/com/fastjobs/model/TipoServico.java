package br.com.fastjobs.model;

public enum TipoServico {
	SERVICO("servico"), 
	MAODEOBRA("Mão de obra");
	
	private final String tipo;
	private TipoServico(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
