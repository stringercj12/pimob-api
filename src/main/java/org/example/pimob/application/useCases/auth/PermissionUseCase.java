package org.example.pimob.application.useCases.auth;

import org.example.pimob.domain.entities.PermissionEntity;
import org.example.pimob.domain.entities.TeamEntity;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissionUseCase {

  private final UserRepository userRepository;

  public PermissionUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean hasPermission(UUID userId, String permissionName) {
    UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

    for (TeamEntity team : user.getTeams()) {
      for (PermissionEntity permission : team.getPermissions()) {
        if (permission.getName().equalsIgnoreCase(permissionName)) {
          return true;
        }
      }
    }

    return false;
  }
}
