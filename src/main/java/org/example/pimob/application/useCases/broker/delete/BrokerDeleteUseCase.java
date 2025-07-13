package org.example.pimob.application.useCases.broker.delete;

import org.example.pimob.exception.broker.BrokerNotFoundException;
import org.example.pimob.infrastructure.repositories.BrokerRepository;
import org.springframework.stereotype.Service;

@Service
public class BrokerDeleteUseCase implements IBrokerDeleteUseCase{

  private final BrokerRepository brokerRepository;

  public BrokerDeleteUseCase(BrokerRepository brokerRepository) {
    this.brokerRepository = brokerRepository;
  }

  @Override
  public void execute(Long id) {
    var broker = this.brokerRepository.findById(id).orElseThrow(() -> new BrokerNotFoundException("Corretor não encontrado"));

    this.brokerRepository.delete(broker);
  }
}
