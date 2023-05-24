package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller.exception;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String message) {
        super(message);
    }
}