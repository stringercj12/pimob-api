package org.example.pimob.exception;

import org.example.pimob.exception.broker.BrokerAlreadyExistsException;
import org.example.pimob.exception.broker.BrokerNotFoundException;
import org.example.pimob.exception.broker.BrokerUnauthorizedUpdateException;
import org.example.pimob.exception.others.BusinessRuleException;
import org.example.pimob.exception.others.ForbiddenException;
import org.example.pimob.exception.others.ValidationException;
import org.example.pimob.exception.property.PropertyDuplicateException;
import org.example.pimob.exception.property.PropertyNotFoundException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<MessageExceptionDTO> handlerUserNotFoundException(UserNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
      new MessageExceptionDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(BrokerNotFoundException.class)
  public ResponseEntity<MessageExceptionDTO> handlerBrokerNotFoundException(BrokerNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
      new MessageExceptionDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(BrokerAlreadyExistsException.class)
  public ResponseEntity<MessageExceptionDTO> handlerBrokerAlreadyExistsException(BrokerAlreadyExistsException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
      new MessageExceptionDTO(HttpStatus.CONFLICT.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(BrokerUnauthorizedUpdateException.class)
  public ResponseEntity<MessageExceptionDTO> handlerUnauthorizedBrokerUpdateException(BrokerUnauthorizedUpdateException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new MessageExceptionDTO(HttpStatus.FORBIDDEN.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(PropertyDuplicateException.class)
  public ResponseEntity<MessageExceptionDTO> handlerDuplicatePropertyException(PropertyDuplicateException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new MessageExceptionDTO(HttpStatus.CONFLICT.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<MessageExceptionDTO> handlerForbiddenException(ForbiddenException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new MessageExceptionDTO(HttpStatus.FORBIDDEN.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<MessageExceptionDTO> handlerValidationException(ValidationException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new MessageExceptionDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<MessageExceptionDTO> handlerBusinessRuleException(BusinessRuleException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new MessageExceptionDTO(HttpStatus.FORBIDDEN.value(), exception.getMessage())
    );
  }

  @ExceptionHandler(PropertyNotFoundException.class)
  public ResponseEntity<MessageExceptionDTO> handlerPropertyNotFoundException(PropertyNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new MessageExceptionDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage())
    );
  }
}
