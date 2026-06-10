package br.com.unisagrado.Unisagrado.unieventos.fotos.exception;

public class InvalidFotoFormatException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidFotoFormatException(String message) {
        super(message);
    }
}
