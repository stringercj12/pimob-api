package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historico_visitas")
public class VisitHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime dataVisita;

  private String anotacoes;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Client cliente;

  @ManyToOne
  @JoinColumn(name = "corretor_id")
  private Broker corretor;

  @ManyToOne
  @JoinColumn(name = "imovel_id")
  private Property property;
}
