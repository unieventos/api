package br.com.unisagrado.Unisagrado.unieventos.fotos.exception;

public class GenericException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GenericException(Exception e) {
		super(e);
	}
}
