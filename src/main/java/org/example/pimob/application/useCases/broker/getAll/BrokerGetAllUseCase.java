package org.example.pimob.application.useCases.broker.getAll;

import org.example.pimob.communication.response.BrokerAllResponse;
import org.example.pimob.infrastructure.repositories.BrokerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerGetAllUseCase implements IBrokerGetAllUseCase {

  private final BrokerRepository brokerRepository;

  public BrokerGetAllUseCase(BrokerRepository brokerRepository) {
    this.brokerRepository = brokerRepository;
  }

  @Override
  public List<BrokerAllResponse> execute() {
    var brokers = this.brokerRepository.findAll();

    return brokers.stream().map(broker -> new BrokerAllResponse(broker.getId(), broker.getCreci(), broker.getTelefone(), broker.getUser().getId())).toList();
  }
}
