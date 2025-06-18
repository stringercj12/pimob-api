package org.example.pimob.communication.request;

import jakarta.validation.constraints.NotNull;

public record BrokerRegisterRequest(
  @NotNull(message = "Informe um creci válido")
  String creci,

  @NotNull(message = "Informe um telefone válido")
  String telefone,

  @NotNull(message = "Informe o id de um usuário válido")
  Long usuarioId
) {
  public static BrokerRegisterRequest toEntity(BrokerRegisterRequest request) {
    return new BrokerRegisterRequest(
      request.creci(),
      request.telefone(),
      request.usuarioId()
    );
  }
}
