package org.example.pimob.application.useCases.roles;

import org.example.pimob.communication.request.RoleRequest;
import org.example.pimob.domain.entities.PermissionEntity;
import org.example.pimob.domain.entities.TeamEntity;
import org.example.pimob.exception.role.RoleExistsException;
import org.example.pimob.infrastructure.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleCreateUseCase implements IRoleCreateUseCase {


  private final RoleRepository roleRepository;

  public RoleCreateUseCase(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void execute(RoleRequest roleRequest) {
    var isExistsRole = roleRepository.findByName(roleRequest.name()).isPresent();


    if (isExistsRole) {
      throw new RoleExistsException("A role with that name already exists");
    }


    var newRole = new TeamEntity();

    newRole.setName(roleRequest.name());
    newRole.setDescription(roleRequest.description());

    var permissions = roleRequest.permissions().stream().map(permission -> new PermissionEntity(null,
            permission.name(),
            permission.description()
    )).toList();

    System.out.println(permissions);


//    newRole.setPermissions(permissions);

    roleRepository.save(newRole);

  }
}
