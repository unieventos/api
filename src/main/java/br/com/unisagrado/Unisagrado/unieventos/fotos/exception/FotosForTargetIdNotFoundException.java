package br.com.unisagrado.Unisagrado.unieventos.fotos.exception;

public class FotosForTargetIdNotFoundException extends RuntimeException{

	public FotosForTargetIdNotFoundException(String targetId){
		super("Fotos for targetid: "+targetId+" not found.");
	}
}
