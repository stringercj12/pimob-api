package org.example.pimob.communication.response;

import org.example.pimob.domain.entities.Broker;

public record BrokerResponse(Long id, String creci, String telefone, UserInBrokerResponse user) {

  public static BrokerResponse toDTO(Broker broker){
    return new BrokerResponse(
      broker.getId(),
      broker.getCreci(),
      broker.getTelefone(),
      new UserInBrokerResponse(
        broker.getUser().getId(),
        broker.getUser().getNome(),
        broker.getUser().getEmail(),
        broker.getUser().getTipoDeUsuario(),
        broker.getUser().getStatusUsuario(),
        broker.getUser().getAtivo()
      )
    );
  }

}
