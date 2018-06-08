package br.com.fastjobs.exception;

public class RecursoInexistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RecursoInexistenteException(String mensagem) {
		super(mensagem);
	}
}
