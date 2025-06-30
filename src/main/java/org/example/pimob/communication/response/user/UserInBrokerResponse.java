package org.example.pimob.communication.response.user;

import org.example.pimob.domain.entities.User;

public record UserInBrokerResponse(
  Long id,
  String nome,
  String email,
  User.TipoDeUsuario tipoDeUsuario,
  User.StatusUsuario statusUsuario,
  Boolean ativo
) {}