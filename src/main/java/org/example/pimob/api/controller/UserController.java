package org.example.pimob.api.controller;

import org.example.pimob.application.useCases.user.getAll.UserGetAllUseCase;
import org.example.pimob.application.useCases.user.getById.UserGetByIdUseCase;
import org.example.pimob.application.useCases.user.register.UserRegisterUseCase;
import org.example.pimob.communication.request.UserRegisterRequest;
import org.example.pimob.communication.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRegisterUseCase userRegisterUseCase;

  private final UserGetAllUseCase userGetAllUseCase;
  private final UserGetByIdUseCase userGetByIdUseCase;

  public UserController(UserRegisterUseCase userRegisterUseCase, UserGetAllUseCase userGetAllUseCase, UserGetByIdUseCase userGetByIdUseCase) {
    this.userRegisterUseCase = userRegisterUseCase;
    this.userGetAllUseCase = userGetAllUseCase;
    this.userGetByIdUseCase = userGetByIdUseCase;
  }

  @PostMapping
  public ResponseEntity<String> Post(@RequestBody UserRegisterRequest request) {
    userRegisterUseCase.execute(request);
    return ResponseEntity.ok("User registered successfully");
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> findAllUsers(){
    return ResponseEntity.ok(userGetAllUseCase.execute());
  }

  @GetMapping("{id}")
  public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
    var result = userGetByIdUseCase.execute(id);

    return ResponseEntity.ok(result);
  }

}
