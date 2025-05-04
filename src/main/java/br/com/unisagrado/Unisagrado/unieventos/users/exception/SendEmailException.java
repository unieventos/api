package br.com.unisagrado.Unisagrado.unieventos.users.exception;

public class SendEmailException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SendEmailException(Exception ex) {
		super(ex);
	}
}
