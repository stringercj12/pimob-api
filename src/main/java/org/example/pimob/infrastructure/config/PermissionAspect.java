package org.example.pimob.infrastructure.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.pimob.application.useCases.auth.PermissionUseCase;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.exception.auth.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Aspect
public class PermissionAspect {

  private final PermissionUseCase permissionUseCase;

  @Autowired
  public PermissionAspect(PermissionUseCase permissionUseCase) {
    this.permissionUseCase = permissionUseCase;
  }

  @Before("@annotation(hasPermission)")
  public void checkPermission(JoinPoint joinPoint, HasPermission permission) throws Throwable {

    UUID userId = getAuthenticatedUserId();

    String requiredPermission = permission.value();

    boolean hasPermission = permissionUseCase.hasPermission(userId, requiredPermission);

    if (!hasPermission) {
      throw new AccessDeniedException("Usuário não tem a permissão necessária: " + requiredPermission);
    }

  }


  private UUID getAuthenticatedUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserEntity user = (UserEntity) authentication.getPrincipal();
    return user.getId();
//    return UUID.randomUUID();
  }

}
