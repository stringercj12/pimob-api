package org.example.pimob.exception.broker;

public class BrokerNotFoundException extends RuntimeException{
  public BrokerNotFoundException(String message) {
    super(message);
  }
}
