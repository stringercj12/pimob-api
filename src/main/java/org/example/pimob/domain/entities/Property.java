package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "imoveis")
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  private String descricao;

  private String endereco;

  private String cidade;

  private String estado;

  private Double preco;

  @Enumerated(EnumType.STRING)
  private TipoImovel tipo;

  @Enumerated(EnumType.STRING)
  private StatusImovel status;

  private LocalDateTime createAt = LocalDateTime.now();


  public enum TipoImovel {
    CASA, APARTAMENTO, TERRENO, COMERCIAL
  }

  public enum StatusImovel {
    DISPONIVEL, VENDIDO, ALUGADO
  }
}
