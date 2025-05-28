package org.example.pimob.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<CreateCorretorExceptioonDto> handlerRuntimeException(RuntimeException exception) {
    CreateCorretorExceptioonDto createCorretorExceptioonDto = new CreateCorretorExceptioonDto(
        HttpStatus.BAD_REQUEST.value(),
        exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createCorretorExceptioonDto);
  }
}
