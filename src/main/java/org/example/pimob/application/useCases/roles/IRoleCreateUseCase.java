package org.example.pimob.application.useCases.roles;


import org.example.pimob.communication.request.RoleRequest;

public interface IRoleCreateUseCase {
  void execute(RoleRequest roleRequest);
}
