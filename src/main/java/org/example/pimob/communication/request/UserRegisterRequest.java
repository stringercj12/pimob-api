package org.example.pimob.communication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.pimob.domain.entities.User.StatusUsuario;
import org.example.pimob.domain.entities.User.TipoDeUsuario;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

  @NotBlank(message = "O campo nome é obrigatório")
  private String nome;
  @NotBlank(message = "O campo email é obrigatório")
  @Email(message = "Informe um email válido")
  private String email;


  private String senha;

  private TipoDeUsuario tipoDeUsuario;
  private StatusUsuario statusUsuario;
}
