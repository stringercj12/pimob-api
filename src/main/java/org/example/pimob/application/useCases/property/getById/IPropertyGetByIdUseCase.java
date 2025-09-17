package org.example.pimob.application.useCases.property.getById;

import org.example.pimob.communication.response.property.PropertyResponse;

public interface IPropertyGetByIdUseCase {
  PropertyResponse execute(Long id);
}
