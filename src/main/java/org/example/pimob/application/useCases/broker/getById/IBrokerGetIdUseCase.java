package org.example.pimob.application.useCases.broker.getById;

import org.example.pimob.communication.response.BrokerResponse;

import java.util.Optional;

public interface IBrokerGetIdUseCase {
   Optional<BrokerResponse> execute(Long id);
}
