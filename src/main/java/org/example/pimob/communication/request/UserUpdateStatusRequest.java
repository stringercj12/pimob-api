package org.example.pimob.communication.request;

import org.example.pimob.domain.entities.UserEntity;

import java.util.UUID;

public record UserUpdateStatusRequest(
        UUID id,
        UserEntity.StatusUsuario statusUsuario
) {
}
