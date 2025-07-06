package org.example.pimob.communication.response.property;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.example.pimob.domain.entities.Property;

public record PropertyRequest(
        @NotBlank(message = "Informe a título do imóvel")
        String titulo,

        @NotBlank(message = "Informe a descrição do imóvel")
        String descricao,

        @NotBlank(message = "Informe o endereço")
  String endereco,

  @NotBlank(message = "Informe a cidade")
  String cidade,

  @NotBlank(message = "Informe o número")
  String numero,

  @NotBlank(message = "Informe o bairro")
  String bairro,

  @NotBlank(message = "Informe o cep")
  String cep,

  @NotBlank(message = "Informe o estado")
  String estado,

  @NotBlank(message = "Qual o valor do imóvel. Venda ou Aluguel")
  Double precoDeVendaOuAluguel,

  String latitude,
  String longitude,

  @NotBlank(message = "Informe a quantidade de comodos")
  Integer numeroDeQuartos,

  @NotBlank(message = "Usuário que esta cadastrando no momento")
  Long criadoPor,

  @NotBlank(message = "Informe o usuário que é dono do imóvel. Ex: Corretor, Cliente")
  Long cadastradoPara,

  @NotBlank(message = "Informe o tipo de usuário")
  String tipoDeUsuario,

  @NotBlank(message = "Informe o telefone de contato")
  String telefone,

  @NotBlank(message = "Informe o nome do contato")
  String nomeDeContato,

  Integer vagasNaGaragem,
  Boolean estaBloqueado,

  @Schema(description = "Tipo do imóvel. Ex: RESIDENCIAL, COMERCIAL, RURAL")
  @NotBlank(message = "Informe o tipo de imóvel. Ex: RESIDENCIAL, COMERCIAL, RURAL")
  Property.TipoDeImovel tipoDeImovel,

  Property.StatusImovel statusDoImovel
) {
}
