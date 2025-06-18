package org.example.pimob.exception;

public class BrokerNotFoundException extends RuntimeException{
  public BrokerNotFoundException(String message) {
    super(message);
  }
}
