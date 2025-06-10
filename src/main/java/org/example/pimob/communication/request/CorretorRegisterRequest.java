package org.example.pimob.communication.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CorretorRegisterRequest {

  @NotNull(message = "Informe um creci válido")
  private String creci;

  @NotNull(message = "Informe um telefone válido")
  private String telefone;

  @NotNull(message = "Informe o id de um usuário válido")
  private Long usuarioId;
}
