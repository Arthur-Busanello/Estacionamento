package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
