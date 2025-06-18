package org.example.pimob.communication.response;

import org.example.pimob.domain.entities.User;
import org.example.pimob.domain.entities.User.TipoDeUsuario;
import org.example.pimob.domain.entities.User.StatusUsuario;

import java.time.LocalDateTime;

public record UserResponse(
  Long id,
  String nome,
  String email,
  TipoDeUsuario tipoDeUsuario,
  StatusUsuario statusUsuario,
  Boolean ativo,
  LocalDateTime createAt,
  LocalDateTime updatedAt
) {

  public static UserResponse toDTO(User user) {

    return new UserResponse(
      user.getId(),
      user.getNome(),
      user.getEmail(),
      user.getTipoDeUsuario(),
      user.getStatusUsuario(),
      user.getAtivo(),
      user.getCreateAt(),
      user.getUpdatedAt()
    );
  }
}
