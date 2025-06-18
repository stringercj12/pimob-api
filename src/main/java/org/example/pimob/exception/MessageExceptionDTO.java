package org.example.pimob.exception;

public record MessageExceptionDTO(Integer httpStatus, String message) {
}
