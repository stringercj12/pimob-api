package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_imoveis")
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  private String descricao;

  private String endereco;

  private String cidade;

  private String cep;

  private String bairro;

  private String estado;

  private Double precoDeVendaOuAluguel;

  private String latitude;
  private String longitude;

  private Integer numeroDeQuartos;
  private Integer vagasNaGarangem;

  private Boolean estaBloqueado;

  @OneToMany(mappedBy = "imovel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<PropertyFile> arquivosDoImovel;

  @Enumerated(EnumType.STRING)
  private TipoImovel tipo;

  @Enumerated(EnumType.STRING)
  private StatusImovel status;

  @CreationTimestamp
  private LocalDateTime createAt = LocalDateTime.now();

  @UpdateTimestamp
  private LocalDateTime updateAt = LocalDateTime.now();

  public enum TipoImovel {
    CASA, APARTAMENTO, TERRENO, COMERCIAL
  }

  public enum StatusImovel {
    DISPONIVEL, VENDIDO, ALUGADO, IRREGULAR
  }
}
