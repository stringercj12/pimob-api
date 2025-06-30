package org.example.pimob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_arquivos_do_imovel")
public class PropertyFile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String nomeOriginal;

  private String extensao;

  private String caminho;

  private Long tamanho;

  private LocalDateTime dataUpload;

  @Enumerated(EnumType.STRING)
  private TipoArquivo tipoArquivo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "imovel_id", nullable = false)
  private Property imovel;

  public enum TipoArquivo {
    FOTO,
    DOCUMENTO,
    PLANTA_BAIXA,
    CONTRATO,
    OUTRO;
  }
}