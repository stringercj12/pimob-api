package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatusUsuario statusUsuario;

  @Column(nullable = true)
  private String creci;

  private String telefone;

  private String observacao;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "tb_user_teams",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "team_id")
  )
  private Set<TeamEntity> teams;

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
