package org.example.pimob.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.pimob.application.useCases.user.delete.UserDeleteUseCase;
import org.example.pimob.application.useCases.user.getAll.UserGetAllUseCase;
import org.example.pimob.application.useCases.user.getById.UserGetByIdUseCase;
import org.example.pimob.application.useCases.user.register.UserRegisterUseCase;
import org.example.pimob.application.useCases.user.update.UserUpdateUseCase;
import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.communication.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Operações relacionados ao usuário")
public class UserController {

  private final UserRegisterUseCase userRegisterUseCase;
  private final UserUpdateUseCase userUpdateUseCase;
  private final UserDeleteUseCase userDeleteUseCase;
  private final UserGetAllUseCase userGetAllUseCase;
  private final UserGetByIdUseCase userGetByIdUseCase;

  public UserController(UserRegisterUseCase userRegisterUseCase, UserUpdateUseCase userUpdateUseCase, UserDeleteUseCase userDeleteUseCase, UserGetAllUseCase userGetAllUseCase, UserGetByIdUseCase userGetByIdUseCase) {
    this.userRegisterUseCase = userRegisterUseCase;
    this.userUpdateUseCase = userUpdateUseCase;
    this.userDeleteUseCase = userDeleteUseCase;
    this.userGetAllUseCase = userGetAllUseCase;
    this.userGetByIdUseCase = userGetByIdUseCase;
  }

  @PostMapping
  @Operation(summary = "Cadastrar usuário")
  public ResponseEntity<String> postUser(@Valid @RequestBody UserRegisterRequest request) {
    userRegisterUseCase.execute(request);
    return ResponseEntity.ok("User registered successfully");
  }

  @PutMapping("/{id}")
  @Operation(summary = "Atualizar usuário")
  public ResponseEntity<Object> updateUser(@Valid @RequestBody UserRegisterRequest request, @PathVariable Long id) {
    userUpdateUseCase.execute(request, id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  @Operation(summary = "Listar todos os usuários")
  public ResponseEntity<List<UserResponse>> findAllUsers() {
    return ResponseEntity.ok(userGetAllUseCase.execute());
  }

  @GetMapping("{id}")
  @Operation(summary = "Buscar usuário pelo ID")
  public ResponseEntity<UserResponse> findUserById(@PathVariable Long id) {
    var result = userGetByIdUseCase.execute(id);

    return ResponseEntity.ok(result);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "Remover um usuário pelo ID")
  public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
    userGetByIdUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }

}
