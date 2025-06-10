package org.example.pimob.api.controller;

import jakarta.validation.Valid;
import org.example.pimob.application.services.CorretorService;
import org.example.pimob.application.useCases.broker.BrokerRegisterUseCase;
import org.example.pimob.communication.request.CorretorRegisterRequest;
import org.example.pimob.communication.response.CorretorRegisterResponse;
import org.example.pimob.communication.response.CorretorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corretor")
public class CorretorController {

  @Autowired
  private BrokerRegisterUseCase brokerRegisterUseCase;

  @PostMapping
  public ResponseEntity<CorretorRegisterResponse> CreateBroker(@RequestBody @Valid CorretorRegisterRequest request) {
    var result = brokerRegisterUseCase.Execute(request);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CorretorResponse> GetBroker(@RequestParam Long brokerId){
    var result = brokerRegisterUseCase.get(brokerId);

    return ResponseEntity.ok(result);
  }
}
