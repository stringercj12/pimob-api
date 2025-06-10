package org.example.pimob.application.useCases.broker;

import org.example.pimob.communication.request.CorretorRegisterRequest;
import org.example.pimob.communication.response.CorretorRegisterResponse;

public interface IBrokerRegisterUseCase {
  public CorretorRegisterResponse Execute(CorretorRegisterRequest corretorRegisterRequest);
}
