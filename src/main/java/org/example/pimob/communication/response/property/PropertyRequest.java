package org.example.pimob.communication.response.property;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.pimob.domain.entities.Property;

import java.util.Optional;

public record PropertyRequest(
        Optional<Long> id,

        @NotBlank(message = "Informe o título do imóvel")
        String titulo,

        @NotBlank(message = "Informe a descrição do imóvel")
        String descricao,

        @NotBlank(message = "Informe o endereço")
        String logradouro,

        @NotBlank(message = "Informe a cidade")
        String cidade,

        @NotBlank(message = "Informe o número")
        String numero,

        String complemento,

        @NotBlank(message = "Informe o bairro")
        String bairro,

        @NotBlank(message = "Informe o cep")
        String cep,

        @NotBlank(message = "Informe o estado")
        String estado,

        @NotNull(message = "Qual o valor do imóvel. Venda ou Aluguel")
        @Positive(message = "O preço de venda/aluguel deve ser um valor positivo.")
        Double precoDeVendaOuAluguel,

        String latitude,
        String longitude,

        @NotNull(message = "Informe a quantidade de cômodos")
        @Positive(message = "O número de quartos deve ser um valor positivo.")
        Integer numeroDeQuartos,

        @NotNull(message = "Usuário que está cadastrando no momento é obrigatório.")
        Long criadoPorUserId,

        @NotNull(message = "Informe o usuário que é dono do imóvel. Ex: Corretor, Cliente")
        Long criadoParaUserId,

        @NotBlank(message = "Informe o tipo de usuário")
        String tipoDeUsuario,

        @NotBlank(message = "Informe o telefone de contato")
        String telefone,

        @NotBlank(message = "Informe o nome do contato")
        String nomeDeContato,

        Integer vagasNaGaragem,
        Boolean estaBloqueado,

        @Schema(description = "Tipo do imóvel. Ex: RESIDENCIAL, COMERCIAL, RURAL")
        @NotNull(message = "Informe o tipo de imóvel. Ex: RESIDENCIAL, COMERCIAL, RURAL")
        Property.TipoDeImovel tipoDeImovel,

        @NotNull(message = "Informe o status do imóvel.")
        Property.StatusDoImovel statusDoImovel
) {
}