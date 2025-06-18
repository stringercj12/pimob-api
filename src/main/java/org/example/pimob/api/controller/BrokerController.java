package org.example.pimob.api.controller;

import jakarta.validation.Valid;
import org.example.pimob.application.useCases.broker.getAll.BrokerGetAllUseCase;
import org.example.pimob.application.useCases.broker.getById.BrokerGetIdUseCase;
import org.example.pimob.application.useCases.broker.register.BrokerRegisterUseCase;
import org.example.pimob.communication.request.BrokerRegisterRequest;
import org.example.pimob.communication.response.BrokerAllResponse;
import org.example.pimob.communication.response.BrokerRegisterResponse;
import org.example.pimob.communication.response.BrokerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/corretor")
public class BrokerController {

  private final BrokerRegisterUseCase brokerRegisterUseCase;

  private final BrokerGetIdUseCase brokerGetIdUseCase;

  private final BrokerGetAllUseCase brokerGetAllUseCase;

  public BrokerController(BrokerRegisterUseCase brokerRegisterUseCase, BrokerGetIdUseCase brokerGetIdUseCase, BrokerGetAllUseCase brokerGetAllUseCase) {
    this.brokerRegisterUseCase = brokerRegisterUseCase;
    this.brokerGetIdUseCase = brokerGetIdUseCase;
    this.brokerGetAllUseCase = brokerGetAllUseCase;
  }

  @PostMapping
  public ResponseEntity<BrokerRegisterResponse> createBroker(@RequestBody @Valid BrokerRegisterRequest request) {
    var result = brokerRegisterUseCase.execute(request);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<BrokerResponse>> findBrokerById(@PathVariable Long id){
    var result = brokerGetIdUseCase.execute(id);

    return ResponseEntity.ok(result);
  }

  @GetMapping
  public ResponseEntity<List<BrokerAllResponse>> findAllBrokers(){
    return ResponseEntity.ok(brokerGetAllUseCase.execute());
  }
}
