package org.example.pimob.exception;

public class CreateCorretorException extends RuntimeException {

  public CreateCorretorException() {
    super("Erro ao criar corretor");
  }

  public CreateCorretorException(final String message) {
    super(message);
  }
}
