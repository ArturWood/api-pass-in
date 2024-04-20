package br.com.dev.api.pass.in.infra.exception;

public class AttendeeAlreadyRegisteredException extends RuntimeException {
    public AttendeeAlreadyRegisteredException(String message) {
        super(message);
    }
}
