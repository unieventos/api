package br.com.unisagrado.Unisagrado.unieventos.storage.exception;

public class SaveFileException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveFileException(Exception e) {
		super(e);
	}
}
