package org.example.pimob.communication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.pimob.domain.entities.UserEntity.StatusUsuario;

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

  private StatusUsuario statusUsuario;
}
