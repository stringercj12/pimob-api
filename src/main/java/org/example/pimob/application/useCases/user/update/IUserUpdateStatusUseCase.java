package org.example.pimob.application.useCases.user.update;

import org.example.pimob.communication.request.UserUpdateStatusRequest;

public interface IUserUpdateStatusUseCase {
    void execute(UserUpdateStatusRequest request);
}
