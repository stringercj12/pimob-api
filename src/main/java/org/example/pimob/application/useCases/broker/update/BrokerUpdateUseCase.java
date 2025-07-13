package org.example.pimob.application.useCases.broker.update;

import org.example.pimob.communication.request.BrokerRegisterRequest;
import org.example.pimob.domain.entities.User;
import org.example.pimob.exception.broker.BrokerNotFoundException;
import org.example.pimob.exception.broker.BrokerUnauthorizedUpdateException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.BrokerRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BrokerUpdateUseCase implements IBrokerUpdateUseCase {

  private final BrokerRepository brokerRepository;
  private final UserRepository userRepository;

  public BrokerUpdateUseCase(BrokerRepository brokerRepository, UserRepository userRepository) {
    this.brokerRepository = brokerRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void execute(BrokerRegisterRequest request, Long id) {
    var broker = this.brokerRepository.findById(id).orElseThrow(() -> new BrokerNotFoundException("Corrertor não encontrado"));

    var user = this.userRepository.findById(broker.getUser().getId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    var isTheBrokerUser = broker.getUser().getId().equals(request.usuarioId());
    var isUserAdmin = user.getStatusUsuario().equals(User.StatusUsuario.ATIVO) && user.getTipoDeUsuario().equals(User.TipoDeUsuario.ADMINISTRADOR);

    if (!isTheBrokerUser && !isUserAdmin) {
      throw new BrokerUnauthorizedUpdateException("Você não tem permissão para atualizar este corretor");
    }

    broker.setCreci(request.creci());
    broker.setTelefone(request.telefone());

    brokerRepository.save(broker);

  }
}
