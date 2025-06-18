package org.example.pimob.communication;

import lombok.Data;
import org.example.pimob.domain.entities.User;

@Data
public class UserDto {
  private Long id;
  private String nome;
  private String email;
  private String senha;
  private User.TipoDeUsuario tipoDeUsuario;
  private User.StatusUsuario statusUsuario;
  private Boolean ativo;
}
