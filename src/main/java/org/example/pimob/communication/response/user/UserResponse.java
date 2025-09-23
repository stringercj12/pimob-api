package org.example.pimob.communication.response.user;

import org.example.pimob.domain.entities.TeamEntity;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.domain.entities.UserEntity.StatusUsuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String nome,
        String email,
        List<TeamEntity> teams,
        StatusUsuario statusUsuario,
        LocalDateTime createAt,
        LocalDateTime updatedAt
) {

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
}
