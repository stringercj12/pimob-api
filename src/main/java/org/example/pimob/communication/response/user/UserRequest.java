package org.example.pimob.communication.response.user;

import org.example.pimob.domain.entities.TeamEntity;
import org.example.pimob.domain.entities.UserEntity.StatusUsuario;

import java.util.List;

public record UserRequest(
        String name,
        String email,
        String senha,
        List<TeamEntity> teams,
        StatusUsuario statusUsuario) {
}
