package org.example.pimob.application.useCases.broker.getById;

import org.example.pimob.communication.response.broker.BrokerResponse;
import org.example.pimob.exception.BrokerNotFoundException;
import org.example.pimob.infrastructure.repositories.BrokerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrokerGetIdUseCase implements IBrokerGetIdUseCase {

  private final BrokerRepository brokerRepository;

  public BrokerGetIdUseCase(BrokerRepository brokerRepository) {
    this.brokerRepository = brokerRepository;
  }

  @Override
  public Optional<BrokerResponse> execute(Long id) {
    var broker = brokerRepository.findById(id).orElseThrow(() -> new BrokerNotFoundException("Broker not found"));

    return Optional.of(BrokerResponse.toDTO(broker));
  }
}
