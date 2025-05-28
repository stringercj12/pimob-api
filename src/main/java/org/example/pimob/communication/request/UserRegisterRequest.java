package org.example.pimob.communication.request;

import org.example.pimob.domain.entities.Usuario.StatusUsuario;
import org.example.pimob.domain.entities.Usuario.TipoDeUsuario;

import lombok.Data;

@Data
public class UserRegisterRequest {
  private String nome;
  private String email;
  private String senha;
  private TipoDeUsuario tipoDeUsuario;
  private StatusUsuario statusUsuario;
  private Boolean ativo;
}
