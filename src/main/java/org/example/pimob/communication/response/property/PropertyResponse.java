package org.example.pimob.communication.response.property;

import org.example.pimob.domain.entities.Property;


public record PropertyResponse(
        Long id,
        String titulo,
        String descricao,
        String endereco,
        String cidade,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String estado,
        Double precoDeVendaOuAluguel,
        String latitude,
        String longitude,
        Integer numeroDeQuartos,
        Long criadoPor,
        Long cadastradoPara,
        String telefone,
        String nomeDeContato,
        Integer vagasNaGaragem,
        Boolean estaBloqueado,
        Property.TipoDeImovel tipoDeImovel,
        Property.StatusDoImovel statusDoImovel
) {
}
