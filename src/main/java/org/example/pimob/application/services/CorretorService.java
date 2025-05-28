package org.example.pimob.application.services;

import org.example.pimob.communication.CorretorDto;
import org.example.pimob.communication.response.CreateCorretorResponse;
import org.example.pimob.domain.entities.Corretor;
import org.example.pimob.domain.entities.Usuario;
import org.example.pimob.infrastructure.repositories.CorretorRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CorretorService {

  private final CorretorRepository _corretorRepository;

  private final UserRepository _userRepository;

  public CorretorService(CorretorRepository corretorRepository, UserRepository userRepository) {
    this._corretorRepository = corretorRepository;
    this._userRepository = userRepository;
  }

  public CreateCorretorResponse CreateBroker(CorretorDto corretorDto) {

    Usuario usuario = _userRepository.findById(corretorDto.getUsuarioId())
        .orElseThrow(
            () -> new RuntimeException("Usuário não encontrado com email: " + corretorDto.getUsuarioId()));

    _corretorRepository.findByUserId(usuario.getId()).ifPresent(c -> {
      throw new RuntimeException("Este usuário já possui um corretor cadastrado.");
    });

    Corretor corretor = new Corretor();
    corretor.setCreci(corretorDto.getCreci());
    corretor.setTelefone(corretorDto.getTelefone());
    corretor.setUsuario(usuario);

    _corretorRepository.save(corretor);

    return new CreateCorretorResponse(
        corretor.getUsuario().getNome(),
        corretor.getUsuario().getEmail(),
        "Corretor Criado com sucesso");
  }

}
