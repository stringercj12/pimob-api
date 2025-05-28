package org.example.pimob.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @Column(unique = true)
  private String email;

  private String senha;

  @Enumerated(EnumType.STRING)
  private TipoDeUsuario tipoDeUsuario;

  @Enumerated(EnumType.STRING)
  private StatusUsuario statusUsuario;

  private Boolean ativo;

  @CreationTimestamp
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @OneToOne(mappedBy = "usuario")
  private Corretor corretor;

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
    ATIVO
  }
}
