package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "corretores")
public class Corretor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String creci;

  private  String telefone;

  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

}
