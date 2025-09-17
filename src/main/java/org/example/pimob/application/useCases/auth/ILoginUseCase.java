package org.example.pimob.application.useCases.auth;

import org.example.pimob.communication.request.AuthRequest;
import org.example.pimob.communication.response.AuthResponse;

public interface ILoginUseCase {
  AuthResponse execute(AuthRequest authRequest);
}
