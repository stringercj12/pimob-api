package org.example.pimob.api.controller;

import org.example.pimob.application.services.CorretorService;
import org.example.pimob.communication.CorretorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corretor")
public class CorretorController {

  @Autowired
  private CorretorService corretorService;

  @PostMapping
  public ResponseEntity<String> CreateBroker(@RequestBody CorretorDto request) {
    corretorService.CreateBroker(request);

    return ResponseEntity.ok("Broker registered successfully");
  }
}
