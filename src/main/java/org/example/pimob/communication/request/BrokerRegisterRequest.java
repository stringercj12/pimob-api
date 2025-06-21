package org.example.pimob.communication.request;

import jakarta.validation.constraints.NotBlank;

public record BrokerRegisterRequest(
  @NotBlank(message = "Informe um creci válido")
  String creci,

  @NotBlank(message = "Informe um telefone válido")
  String telefone,

  @NotBlank(message = "Informe o id de um usuário válido")
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
