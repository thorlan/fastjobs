package br.com.fastjobs.repository.filter;

import br.com.fastjobs.model.TipoServico;
import br.com.fastjobs.model.Usuario;

public class ServicoFilter {

	private String descricao;
	private String nome;
	private Usuario usuario;
	private TipoServico tipoServico;
	
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
