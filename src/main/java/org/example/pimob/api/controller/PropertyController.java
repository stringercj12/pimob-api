package org.example.pimob.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.example.pimob.application.useCases.property.create.PropertyCreateUseCase;
import org.example.pimob.application.useCases.property.getAll.PropertyListAllUseCase;
import org.example.pimob.application.useCases.property.update.PropertyUpdateUseCase;
import org.example.pimob.communication.response.property.PropertyRegisterResponse;
import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.communication.response.property.PropertyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/imovel")
@CrossOrigin("*")
@Tag(name = "Imóvel", description = "Operações relacionadas ao imóvel")
public class PropertyController {

  private final PropertyCreateUseCase propertyCreateUseCase;
  private final PropertyListAllUseCase propertyListAllUseCase;
  private final PropertyUpdateUseCase propertyUpdateUseCase;

  public PropertyController(PropertyCreateUseCase propertyCreateUseCase, PropertyListAllUseCase propertyListAllUseCase, PropertyUpdateUseCase propertyUpdateUseCase) {
    this.propertyCreateUseCase = propertyCreateUseCase;
      this.propertyListAllUseCase = propertyListAllUseCase;
    this.propertyUpdateUseCase = propertyUpdateUseCase;
  }

  @GetMapping
  public ResponseEntity<List<PropertyResponse>> index() {
    List<PropertyResponse> properties = this.propertyListAllUseCase.execute();

    return ResponseEntity.ok(properties);
  }

  @PostMapping
  public ResponseEntity<PropertyRegisterResponse> create(@Valid @RequestBody PropertyRequest request) {
    PropertyRegisterResponse propertyCreate = this.propertyCreateUseCase.execute(request);
    URI location = URI.create("/imovel/" + propertyCreate.id());

    return ResponseEntity.created(location).body(propertyCreate);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@Valid @RequestBody PropertyRequest request, @PathVariable Long id) {
    this.propertyUpdateUseCase.execute(request, id);
    return ResponseEntity.noContent().build();
  }


}
