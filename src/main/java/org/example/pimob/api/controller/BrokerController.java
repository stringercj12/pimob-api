package org.example.pimob.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.pimob.application.useCases.broker.delete.BrokerDeleteUseCase;
import org.example.pimob.application.useCases.broker.getAll.BrokerGetAllUseCase;
import org.example.pimob.application.useCases.broker.getById.BrokerGetIdUseCase;
import org.example.pimob.application.useCases.broker.register.BrokerRegisterUseCase;
import org.example.pimob.application.useCases.broker.update.BrokerUpdateUseCase;
import org.example.pimob.communication.request.BrokerRegisterRequest;
import org.example.pimob.communication.response.broker.BrokerAllResponse;
import org.example.pimob.communication.response.broker.BrokerRegisterResponse;
import org.example.pimob.communication.response.broker.BrokerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/corretor")
@Tag(name = "Corretor", description = "Operações relacionadas ao corretor")
public class BrokerController {

  private final BrokerRegisterUseCase brokerRegisterUseCase;
  private final BrokerGetIdUseCase brokerGetIdUseCase;
  private final BrokerGetAllUseCase brokerGetAllUseCase;
  private final BrokerUpdateUseCase brokerUpdateUseCase;
  private final BrokerDeleteUseCase brokerDeleteUseCase;

  public BrokerController(
    BrokerRegisterUseCase brokerRegisterUseCase,
    BrokerGetIdUseCase brokerGetIdUseCase,
    BrokerGetAllUseCase brokerGetAllUseCase,
    BrokerUpdateUseCase brokerUpdateUseCase,
    BrokerDeleteUseCase brokerDeleteUseCase) {
    this.brokerRegisterUseCase = brokerRegisterUseCase;
    this.brokerGetIdUseCase = brokerGetIdUseCase;
    this.brokerGetAllUseCase = brokerGetAllUseCase;
    this.brokerUpdateUseCase = brokerUpdateUseCase;
    this.brokerDeleteUseCase = brokerDeleteUseCase;
  }

  @PostMapping
  @Operation(summary = "Cadastrar corretor", description = "Cadastrar um novo corretor")
  public ResponseEntity<BrokerRegisterResponse> createBroker(@Valid @RequestBody BrokerRegisterRequest request) {
    var result = brokerRegisterUseCase.execute(request);
    return ResponseEntity.ok(result);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Atualizar corretor", description = "Atualiza um corretor")
  public ResponseEntity<Object> updateBroker(@Valid @RequestBody BrokerRegisterRequest request, @PathVariable Long id) {
    brokerUpdateUseCase.execute(request, id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Buscar corretor por ID", description = "Retorna um corretor de acordo com o ID informado")
  public ResponseEntity<Optional<BrokerResponse>> findBrokerById(@PathVariable Long id) {
    var result = brokerGetIdUseCase.execute(id);
    return ResponseEntity.ok(result);
  }

  @GetMapping
  @Operation(summary = "Listar todos os corretores", description = "Retornar uma lista com todos os corretores cadastrados")
  public ResponseEntity<List<BrokerAllResponse>> findAllBrokers() {
    return ResponseEntity.ok(brokerGetAllUseCase.execute());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletar corretor por ID", description = "Remove um corretor de acordo com o ID informado")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    brokerDeleteUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}
