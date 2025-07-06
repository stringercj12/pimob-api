package org.example.pimob.application.useCases.property.getAll;

import org.example.pimob.communication.response.property.PropertyResponse;

import java.util.List;

public interface IPropertyListAllUseCase {
    List<PropertyResponse> execute();
}
