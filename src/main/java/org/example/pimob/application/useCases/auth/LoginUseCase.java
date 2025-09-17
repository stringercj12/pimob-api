package org.example.pimob.application.useCases.auth;

import org.example.pimob.communication.request.AuthRequest;
import org.example.pimob.communication.response.AuthResponse;
import org.example.pimob.domain.enums.PermissionsEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUseCase implements ILoginUseCase {
  @Override
  public AuthResponse execute(AuthRequest authRequest) {


    return new AuthResponse(
            "token",
            1,
            authRequest.email(),
            List.of(
                    PermissionsEnum.PROPERTY_ADD,
                    PermissionsEnum.USER_CREATE_CLIENT,
                    PermissionsEnum.USER_CREATE_CLIENT
            )
    );
  }
}
