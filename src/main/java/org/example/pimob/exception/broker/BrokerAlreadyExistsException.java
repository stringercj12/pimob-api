package org.example.pimob.exception.broker;

public class BrokerAlreadyExistsException extends RuntimeException {
  public BrokerAlreadyExistsException(String message) {
    super(message);
  }
}
