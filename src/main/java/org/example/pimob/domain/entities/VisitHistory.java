package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_historico_de_visitas")
public class VisitHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime dataDaVisita;

  private String anotacoes;

  @ManyToOne
  @JoinColumn(name = "criado_por_user_id", nullable = false)
  private UserEntity criadoPor;

  @ManyToOne
  @JoinColumn(name = "criado_para_user_id")
  private UserEntity criadoPara;

  @ManyToOne
  @JoinColumn(name = "imovel_id")
  private Property property;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatusDaVisita statusDaVisita;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;


  private enum StatusDaVisita {
    AGENDADA,
    CRIADA,
    REALIZADA,
    CANCELADA,
    ADIADA,
    FINALIZADA;

    public static boolean exists(String status) {
      try {
        VisitHistory.StatusDaVisita.valueOf(status);
        return true;
      } catch (IllegalArgumentException e) {
        return false;
      }
    }
  }
}
