package br.com.unisagrado.Unisagrado.unieventos.users.exception;

public class InvalidOperation extends RuntimeException{

	public InvalidOperation(String operation) {
		super("Operação: "+operation+" inválida para usuarios");
	}
}
