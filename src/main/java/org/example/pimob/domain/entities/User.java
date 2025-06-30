package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

  private Boolean ativo;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Broker corretor;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;


  public enum TipoDeUsuario {
    CORRETOR,
    ADMINISTRADOR,
    CLIENTE,
    USER;
  }

  public enum StatusUsuario {
    PENDENTE,
    EM_PROCESSAMENTO,
    APROVADO,
    REJEITADO,
    ATIVO;
  }

}
