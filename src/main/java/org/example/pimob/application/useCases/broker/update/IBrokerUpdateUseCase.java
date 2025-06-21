package org.example.pimob.application.useCases.broker.update;

import org.example.pimob.communication.request.BrokerRegisterRequest;

public interface IBrokerUpdateUseCase {
  void execute(BrokerRegisterRequest request, Long id);
}
