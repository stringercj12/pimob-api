package org.example.pimob.application.useCases.property.create;

import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.communication.response.property.PropertyResponse;
import org.example.pimob.domain.entities.Property;
import org.example.pimob.domain.entities.User;
import org.example.pimob.exception.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.PropertyRepository;

public class PropertyCreateUseCase implements IPropertyCreateUseCase{

  private final PropertyRepository propertyRepository;

  public PropertyCreateUseCase(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public PropertyResponse execute(PropertyRequest request) {

    if(request.tipoDeUsuario().equals(User.TipoDeUsuario.CLIENTE)){
    user=  this.propertyRepository.findById(request.criadoPor()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    Property property = new Property();

    property.setDescricao(request.descricao());
    property.setNumeroDeQuartos(request.numeroDeQuartos());
    property.setEstaBloqueado(false);
    property.setLatitude(request.latitude());
    property.setLongitude(request.longitude());
    property.setPrecoDeVendaOuAluguel(request.precoDeVendaOuAluguel());
    property.setTipo(request.tipo());
    property.setStatus(request.status());
    property.setEndereco(request.endereco());
    property.setEstado(request.estado());
    property.setCidade(request.cidade());
    property.setBairro(request.bairro());
    property.setCep(request.cep());

    var propertyCreated = this.propertyRepository.save(property);

    return new PropertyResponse(propertyCreated.getId());
  }
}
