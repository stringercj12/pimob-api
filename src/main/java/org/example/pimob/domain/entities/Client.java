package org.example.pimob.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_clientes")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true)
  private String email;

  private String telefone;

  private String observacoes;

  private LocalDateTime createAt = LocalDateTime.now();
}
