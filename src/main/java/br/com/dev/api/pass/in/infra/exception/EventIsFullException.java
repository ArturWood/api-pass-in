package br.com.dev.api.pass.in.infra.exception;

public class EventIsFullException extends RuntimeException {
    public EventIsFullException(String message) {
        super(message);
    }
}
