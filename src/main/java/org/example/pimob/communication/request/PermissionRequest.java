package org.example.pimob.communication.request;

public record PermissionRequest(
        String name,
        String key,
        String description
) {
}
