package org.example.pimob.communication;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CorretorDto {

  private String creci;
  private String telefone;

  @NotNull(message = "Informe o id de um usuário válido")
  private Long usuarioId;
}
