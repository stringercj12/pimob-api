package org.example.pimob.communication.response;

import org.example.pimob.domain.entities.User;

public record UserInBrokerResponse(
  Long id,
  String nome,
  String email,
  User.TipoDeUsuario tipoDeUsuario,
  User.StatusUsuario statusUsuario,
  Boolean ativo
) {}