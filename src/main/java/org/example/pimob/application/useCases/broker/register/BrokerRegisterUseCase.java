package org.example.pimob.application.useCases.broker.register;

import org.example.pimob.communication.request.BrokerRegisterRequest;
import org.example.pimob.communication.response.BrokerRegisterResponse;
import org.example.pimob.domain.entities.Broker;
import org.example.pimob.exception.BrokerAlreadyExistsException;
import org.example.pimob.exception.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.BrokerRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrokerRegisterUseCase implements IBrokerRegisterUseCase {


  private final BrokerRepository brokerRepository;

  private final UserRepository userRepository;

  public BrokerRegisterUseCase(BrokerRepository brokerRepository, UserRepository userRepository) {
    this.brokerRepository = brokerRepository;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public BrokerRegisterResponse execute(BrokerRegisterRequest brokerRegisterRequest) {
    var user = userRepository.findById(brokerRegisterRequest.usuarioId()).orElseThrow(
      () -> new UserNotFoundException("User not found"));

    brokerRepository.findByUserId(user.getId()).ifPresent(broker -> {
      throw new BrokerAlreadyExistsException("This user already has a registered broker.");
    });

    var broker = new Broker(
      null,
      brokerRegisterRequest.creci(),
      brokerRegisterRequest.telefone(),
      user
    );

    Broker savedBroker = brokerRepository.save(broker);

    return new BrokerRegisterResponse(
      savedBroker.getUser().getNome(),
      savedBroker.getUser().getEmail(),
      "Broker registered successfully."
    );
  }
}
