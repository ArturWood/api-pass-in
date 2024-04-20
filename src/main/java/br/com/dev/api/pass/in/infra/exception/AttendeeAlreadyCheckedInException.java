package br.com.dev.api.pass.in.infra.exception;

public class AttendeeAlreadyCheckedInException extends RuntimeException {
    public AttendeeAlreadyCheckedInException(String message) {
        super(message);
    }
}
