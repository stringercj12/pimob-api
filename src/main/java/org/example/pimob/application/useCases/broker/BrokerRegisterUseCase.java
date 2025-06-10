package org.example.pimob.application.useCases.broker;

import org.example.pimob.communication.request.CorretorRegisterRequest;
import org.example.pimob.communication.response.CorretorRegisterResponse;
import org.example.pimob.domain.entities.Corretor;
import org.example.pimob.domain.entities.Usuario;
import org.example.pimob.infrastructure.repositories.CorretorRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BrokerRegisterUseCase implements IBrokerRegisterUseCase{


  private final CorretorRepository _corretorRepository;

  private final UserRepository _userRepository;

  public BrokerRegisterUseCase(CorretorRepository corretorRepository, UserRepository userRepository) {
    this._corretorRepository = corretorRepository;
    this._userRepository = userRepository;
  }

  @Override
  public CorretorRegisterResponse Execute(CorretorRegisterRequest corretorRegisterRequest) {
    Usuario usuario = _userRepository.findById(corretorRegisterRequest.getUsuarioId())
      .orElseThrow(
        () -> new RuntimeException("Usuário não encontrado com email: " + corretorRegisterRequest.getUsuarioId()));

    _corretorRepository.findByBrokerId(usuario.getId()).ifPresent(c -> {
      throw new RuntimeException("Este usuário já possui um corretor cadastrado.");
    });

    Corretor corretor = new Corretor();
    corretor.setCreci(corretorRegisterRequest.getCreci());
    corretor.setTelefone(corretorRegisterRequest.getTelefone());
    corretor.setUsuario(usuario);

    _corretorRepository.save(corretor);

    return new CorretorRegisterResponse(
      corretor.getUsuario().getNome(),
      corretor.getUsuario().getEmail(),
      "Corretor Criado com sucesso");
  }
}
