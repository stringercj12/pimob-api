package org.example.pimob.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imovel")
@Tag(name ="Imóvel", description = "Operações relacionadas ao imóvel")
public class PropertyController {


  @GetMapping
  public String index() {
    return "index";
  }
}
