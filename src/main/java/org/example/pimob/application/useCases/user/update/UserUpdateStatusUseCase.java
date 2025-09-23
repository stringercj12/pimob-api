package org.example.pimob.application.useCases.user.update;

import org.example.pimob.communication.request.UserUpdateStatusRequest;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.exception.others.ValidationException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateStatusUseCase implements IUserUpdateStatusUseCase {

    private final UserRepository userRepository;

    public UserUpdateStatusUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UserUpdateStatusRequest request) {
        var user = userRepository.findById(request.id()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        if (!UserEntity.StatusUsuario.exists(request.statusUsuario().toString())) {
            throw new ValidationException("Informe um status válido");
        }

//        if (request.statusUsuario() != UserEntity.StatusUsuario.REJEITADO || request.statusUsuario() != UserEntity.StatusUsuario.PENDENTE) {
//            user.setAtivo(true);
//        }

        user.setStatusUsuario(request.statusUsuario());

        userRepository.save(user);
    }
}
