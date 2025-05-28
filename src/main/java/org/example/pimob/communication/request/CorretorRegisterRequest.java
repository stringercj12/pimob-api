package org.example.pimob.communication.request;

import lombok.Data;

@Data
public class CorretorRegisterRequest {
  private Long id;
  private String creci;
  private String telefone;
  private Long usuarioId;
}
