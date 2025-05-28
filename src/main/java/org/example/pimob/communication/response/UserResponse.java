package org.example.pimob.communication.response;

import org.example.pimob.domain.entities.Usuario.StatusUsuario;
import org.example.pimob.domain.entities.Usuario.TipoDeUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
  private Long id;
  private String nome;
  private String email;
  private String senha;
  private TipoDeUsuario tipoDeUsuario;
  private StatusUsuario statusUsuario;
  private Boolean ativo;
}
