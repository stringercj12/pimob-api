package org.example.pimob.communication.request;

import org.example.pimob.domain.entities.User;

public record UserUpdateStatusRequest(
        Long id,
        User.StatusUsuario statusUsuario
) {
}
