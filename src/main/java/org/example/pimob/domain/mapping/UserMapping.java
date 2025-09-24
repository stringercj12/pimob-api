package org.example.pimob.domain.mapping;

import org.example.pimob.communication.response.user.UserRequest;
import org.example.pimob.communication.response.user.UserResponse;
import org.example.pimob.domain.entities.UserEntity;

public class UserMapping {

  public static UserResponse toDTO(UserEntity user) {

    return new UserResponse(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getTeams().stream().toList(),
            user.getStatusUsuario(),
            user.getCreateAt(),
            user.getUpdatedAt()
    );
  }

  public static UserEntity toEntity(UserRequest user) {
    return new UserEntity();
  }
}
