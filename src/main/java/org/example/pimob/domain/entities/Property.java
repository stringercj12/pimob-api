package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.pimob.exception.others.BusinessRuleException;
import org.example.pimob.exception.others.ValidationException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.Long;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_imoveis")
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  private String descricao;

  private String logradouro;

  private String cidade;

  private String cep;

  private String numero;

  private String complemento;

  private String bairro;

  private String estado;

  private Double precoDeVendaOuAluguel;

  private String latitude;
  private String longitude;

  private Integer numeroDeQuartos;

  private Integer vagasNaGaragem;

  @ManyToOne
  @JoinColumn(name = "criado_por_user_id", nullable = false)
  private User criadoPor;

  @ManyToOne
  @JoinColumn(name = "criado_para_user_id")
  private User criadoPara;

  private Boolean estaBloqueado;

  private String telefone;

  private String nomeDeContato;

  @OneToMany(mappedBy = "imovel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<PropertyFile> arquivosDoImovel;

  @Enumerated(EnumType.STRING)
  private TipoDeImovel tipoDeImovel;

  @Enumerated(EnumType.STRING)
  private StatusDoImovel statusDoImovel;

  @CreationTimestamp
  private LocalDateTime createAt = LocalDateTime.now();

  @UpdateTimestamp
  private LocalDateTime updateAt = LocalDateTime.now();

  public enum TipoDeImovel {
    CASA, APARTAMENTO, TERRENO, COMERCIAL
  }

  public enum StatusDoImovel {
    DISPONIVEL, VENDA, VENDIDO, ALUGAR, IRREGULAR
  }

  public static Property createNew(
          String titulo, String descricao, String logradouro, String cidade, String cep,
          String numero, String complemento, String bairro, String estado, Double precoDeVendaOuAluguel,
          String latitude, String longitude, Integer numeroDeQuartos,
          Integer vagasNaGaragem, User currentUser, User criadoParaUser, String telefone,
          String nomeDeContato, TipoDeImovel tipoDeImovel) {

    if (titulo == null || titulo.isBlank()) {
      throw new ValidationException("Título é obrigatório.");
    }

    if (precoDeVendaOuAluguel == null || precoDeVendaOuAluguel <= 0) {
      throw new BusinessRuleException("Preço deve ser positivo.");
    }

    Property property = new Property();

    property.setTitulo(titulo);
    property.setDescricao(descricao);
    property.setLogradouro(logradouro);
    property.setCidade(cidade);
    property.setCep(cep);
    property.setNumero(numero);
    property.setComplemento(complemento);
    property.setBairro(bairro);
    property.setEstado(estado);
    property.setPrecoDeVendaOuAluguel(precoDeVendaOuAluguel);
    property.setLatitude(latitude);
    property.setLongitude(longitude);
    property.setNumeroDeQuartos(numeroDeQuartos);
    property.setVagasNaGaragem(vagasNaGaragem);

    property.setCriadoPor(currentUser);

    property.setCriadoPara(criadoParaUser);

    property.setTelefone(telefone);
    property.setNomeDeContato(nomeDeContato);
    property.setTipoDeImovel(tipoDeImovel);

    property.setStatusDoImovel(StatusDoImovel.DISPONIVEL);
    property.setEstaBloqueado(false);
    property.setArquivosDoImovel(new ArrayList<>());

    return property;
  }

  public void bloquearImovel() {
    if (this.estaBloqueado) {
      throw new BusinessRuleException("Imóvel já está bloqueado.");
    }

    this.estaBloqueado = true;
    this.statusDoImovel = StatusDoImovel.IRREGULAR;

  }

  public void mudarStatus(StatusDoImovel novoStatus){
    if (this.estaBloqueado && novoStatus != StatusDoImovel.DISPONIVEL) {
      throw new BusinessRuleException("Não é possível alterar o status de um imóvel bloqueado diretamente.");
    }
    this.statusDoImovel = novoStatus;
  }
}
