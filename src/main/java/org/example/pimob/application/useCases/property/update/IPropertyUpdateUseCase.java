package org.example.pimob.application.useCases.property.update;

import org.example.pimob.communication.response.property.PropertyRequest;

public interface IPropertyUpdateUseCase {
  void execute(PropertyRequest request, Long id);
}
