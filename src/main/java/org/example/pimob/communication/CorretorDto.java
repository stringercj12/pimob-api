package org.example.pimob.communication;

import lombok.Data;

@Data
public class CorretorDto {

  private Long id;
  private String creci;
  private String telefone;
  private Long usuarioId;
}
