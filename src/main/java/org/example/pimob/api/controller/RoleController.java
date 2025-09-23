package org.example.pimob.api.controller;

import org.example.pimob.application.useCases.roles.IRoleCreateUseCase;
import org.example.pimob.communication.request.RoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

  private final IRoleCreateUseCase roleCreateUseCase;


  public RoleController(IRoleCreateUseCase roleCreateUseCase) {
    this.roleCreateUseCase = roleCreateUseCase;
  }

  @PostMapping
  public ResponseEntity createRole(@RequestBody RoleRequest roleRequest) {
    roleCreateUseCase.execute(roleRequest);

    return ResponseEntity.noContent().build();
  }
}
