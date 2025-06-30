package org.example.pimob.application.useCases.broker.register;

import org.example.pimob.communication.request.BrokerRegisterRequest;
import org.example.pimob.communication.response.broker.BrokerRegisterResponse;

public interface IBrokerRegisterUseCase {
  BrokerRegisterResponse execute(BrokerRegisterRequest brokerRegisterRequest);
}
