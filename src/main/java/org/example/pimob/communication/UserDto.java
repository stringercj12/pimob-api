package org.example.pimob.communication;

import lombok.Data;
import org.example.pimob.domain.entities.Usuario;

@Data
public class UserDto {
  private Long id;
  private String nome;
  private String email;
  private String senha;
  private Usuario.TipoDeUsuario tipoDeUsuario;
  private Usuario.StatusUsuario statusUsuario;
  private Boolean ativo;
}
