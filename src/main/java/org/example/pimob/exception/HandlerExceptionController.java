package org.example.pimob.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.example.pimob.exception.auth.AccessDeniedException;
import org.example.pimob.exception.broker.BrokerAlreadyExistsException;
import org.example.pimob.exception.broker.BrokerNotFoundException;
import org.example.pimob.exception.broker.BrokerUnauthorizedUpdateException;
import org.example.pimob.exception.others.BusinessRuleException;
import org.example.pimob.exception.others.ForbiddenException;
import org.example.pimob.exception.others.ValidationException;
import org.example.pimob.exception.property.PropertyDuplicateException;
import org.example.pimob.exception.property.PropertyNotFoundException;
import org.example.pimob.exception.role.RoleExistsException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handlerUserNotFoundException(UserNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", exception.getMessage())
    );
  }

  @ExceptionHandler(BrokerNotFoundException.class)
  public ResponseEntity<ErrorResponse> handlerBrokerNotFoundException(BrokerNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", exception.getMessage())
    );
  }

  @ExceptionHandler(BrokerAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handlerBrokerAlreadyExistsException(BrokerAlreadyExistsException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new ErrorResponse(HttpStatus.CONFLICT.value(), "CONFLICT", exception.getMessage())
    );
  }

  @ExceptionHandler(BrokerUnauthorizedUpdateException.class)
  public ResponseEntity<ErrorResponse> handlerUnauthorizedBrokerUpdateException(BrokerUnauthorizedUpdateException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponse(HttpStatus.FORBIDDEN.value(), "FORBIDDEN", exception.getMessage())
    );
  }

  @ExceptionHandler(PropertyDuplicateException.class)
  public ResponseEntity<ErrorResponse> handlerDuplicatePropertyException(PropertyDuplicateException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new ErrorResponse(HttpStatus.CONFLICT.value(), "CONFLICT", exception.getMessage())
    );
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ErrorResponse> handlerForbiddenException(ForbiddenException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponse(HttpStatus.FORBIDDEN.value(), "FORBIDDEN", exception.getMessage())
    );
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> handlerValidationException(ValidationException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", exception.getMessage())
    );
  }

  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ErrorResponse> handlerBusinessRuleException(BusinessRuleException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponse(HttpStatus.FORBIDDEN.value(), "FORBIDDEN", exception.getMessage())
    );
  }

  @ExceptionHandler(PropertyNotFoundException.class)
  public ResponseEntity<ErrorResponse> handlerPropertyNotFoundException(PropertyNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", exception.getMessage())
    );
  }

  @ExceptionHandler(RoleExistsException.class)
  public ResponseEntity<ErrorResponse> handlerRoleExistsException(RoleExistsException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new ErrorResponse(HttpStatus.CONFLICT.value(), "CONFLICT", exception.getMessage())
    );
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handlerAccessDeniedException(AccessDeniedException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden", exception.getMessage())
    );
  }

  @ExceptionHandler({ExpiredJwtException.class, SignatureException.class, RuntimeException.class})
  public ResponseEntity<ErrorResponse> handleAuthenticationErrors(RuntimeException ex) {

    String message = ex.getMessage();

    if (ex instanceof ExpiredJwtException) {
      message = "Token expirado. Faça o login novamente.";
    } else if (ex instanceof SignatureException) {
      message = "Assinatura do token inválida.";
    } else {
      message = "Token inválido ou ausente. Autenticação falhou.";
    }

    ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            message
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

}
