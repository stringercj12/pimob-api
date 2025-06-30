package org.example.pimob.application.useCases.property.create;

import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.communication.response.property.PropertyResponse;

public interface IPropertyCreateUseCase {
  PropertyResponse execute(PropertyRequest request);
}
