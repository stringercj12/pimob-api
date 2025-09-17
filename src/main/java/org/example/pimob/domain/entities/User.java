package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"corretor"})
@Entity
@Table(name = "tb_users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoDeUsuario tipoDeUsuario;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatusUsuario statusUsuario;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  @Column(nullable = false)
  private Boolean ativo;

  @Column(nullable = true)
  private String creci;

  private String telefone;

  private String observacao;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;


  public enum TipoDeUsuario {
    CORRETOR,
    ADMINISTRADOR,
    CLIENTE,
  }

  public enum StatusUsuario {
    PENDENTE,
    EM_PROCESSAMENTO,
    APROVADO,
    REJEITADO,
    ATIVO;

    public static boolean exists(String status) {
      try {
        StatusUsuario.valueOf(status);
        return true;
      } catch (IllegalArgumentException e) {
        return false;
      }
    }
  }

}
