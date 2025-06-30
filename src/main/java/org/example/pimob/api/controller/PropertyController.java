package org.example.pimob.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.pimob.application.useCases.property.create.PropertyCreateUseCase;
import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.communication.response.property.PropertyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/imovel")
@Tag(name = "Imóvel", description = "Operações relacionadas ao imóvel")
public class PropertyController {

  private final PropertyCreateUseCase propertyCreateUseCase;

  public PropertyController(PropertyCreateUseCase propertyCreateUseCase) {
    this.propertyCreateUseCase = propertyCreateUseCase;
  }

  @GetMapping
  public String index() {
    return "index";
  }

  @PostMapping
  public ResponseEntity<PropertyResponse> create(@Valid @RequestBody PropertyRequest request) {
    PropertyResponse propertyCreate = this.propertyCreateUseCase.execute(request);
    URI location = URI.create("/imovel/" + propertyCreate.id());

    return ResponseEntity.created(location).body(propertyCreate);
  }


}
