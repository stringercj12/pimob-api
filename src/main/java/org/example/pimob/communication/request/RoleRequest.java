package org.example.pimob.communication.request;

import java.util.List;

public record RoleRequest(
        String name,
        String description,
        List<PermissionRequest> permissions
) {
}
