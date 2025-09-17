package org.example.pimob.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.pimob.application.useCases.auth.LoginUseCase;
import org.example.pimob.communication.request.AuthRequest;
import org.example.pimob.communication.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Operações relacionados a autenticação")
public class AuthController {

  private final LoginUseCase loginUseCase;

  public AuthController(LoginUseCase loginUseCase) {
    this.loginUseCase = loginUseCase;
  }

  @PostMapping
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest){
    AuthResponse result = loginUseCase.execute(authRequest);
    return ResponseEntity.ok(result);
  }
}
