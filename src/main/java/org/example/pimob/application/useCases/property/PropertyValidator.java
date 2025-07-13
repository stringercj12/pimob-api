package org.example.pimob.application.useCases.property;

import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.domain.entities.User;
import org.example.pimob.exception.others.BusinessRuleException;
import org.example.pimob.exception.others.ForbiddenException;
import org.example.pimob.exception.property.PropertyDuplicateException;
import org.example.pimob.infrastructure.repositories.PropertyRepository;

public class PropertyValidator {

  private final PropertyRepository propertyRepository;

  public PropertyValidator(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  public void validate(PropertyRequest request, User user) {
    validatePermissions(user);
    if (request.id().isEmpty()) {
      validateDuplicationProperty(request);
    }
    validateIfTheUserIsTheOwnerOrBrokerAdministrator(user, request);
    validateLimitToRegister(user);
  }

  private void validatePermissions(User user) {
    if (!user.getAtivo()) {
      throw new ForbiddenException("Usuário não tem permissão para cadastrar imóveis.");
    }
  }

  private void validateDuplicationProperty(PropertyRequest request) {
    boolean exists = propertyRepository.existsByLogradouroAndNumeroAndComplementoAndCep(request.logradouro(), request.numero(), request.complemento(), request.cep());
    if (exists) {
      throw new PropertyDuplicateException("Imóvel com esse endereço já cadastrado.");
    }
  }

  private void validateIfTheUserIsTheOwnerOrBrokerAdministrator(User user, PropertyRequest request) {
    if (user.getTipoDeUsuario() == User.TipoDeUsuario.CLIENTE && !user.getId().equals(request.criadoPor())) {
      throw new ForbiddenException("Usuário não tem permissão para cadastrar imóveis.");
    }
  }

  private void validateLimitToRegister(User user) {
    if (user.getTipoDeUsuario() == User.TipoDeUsuario.CLIENTE) {
      long totalImoveis = propertyRepository.countByCriadoPor(user.getId());

      if (totalImoveis >= 10) {
        throw new BusinessRuleException("Limite de 10 imóveis por ciente antigido.");
      }
    }
  }
}
