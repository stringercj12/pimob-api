package org.example.pimob.communication.response;

import org.example.pimob.domain.enums.PermissionsEnum;

import java.util.List;

public record AuthResponse(String token, long id, String email, List<PermissionsEnum> permissions) {
}
