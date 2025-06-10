package org.example.pimob.communication.response;

import org.example.pimob.domain.entities.Usuario;

public record CorretorResponse(Long id, String creci, String telefone, Usuario usuario) {
}
