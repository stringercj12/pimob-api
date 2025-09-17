package org.example.pimob.application.useCases.auth;

import org.example.pimob.domain.entities.User;
import org.example.pimob.domain.enums.PermissionsEnum;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class PermissionsUseCase {

  private static final Map<User.TipoDeUsuario, Set<PermissionsEnum>> ROLE_PERMISSIONS = new HashMap<>();

  static {
    ROLE_PERMISSIONS.put(User.TipoDeUsuario.ADMINISTRADOR, EnumSet.of(
            PermissionsEnum.PROPERTY_ADD,
            PermissionsEnum.PROPERTY_VIEW_ALL,
            PermissionsEnum.PROPERTY_EDIT_OWN,
            PermissionsEnum.USER_EDIT_NAME_SELF,
            PermissionsEnum.ADMIN_MANAGE_USERS,
            PermissionsEnum.USER_CREATE_CLIENT,
            PermissionsEnum.USER_VIEW_CLIENTS_OWN
    ));

    ROLE_PERMISSIONS.put(User.TipoDeUsuario.CORRETOR, EnumSet.of(
            PermissionsEnum.PROPERTY_ADD,
            PermissionsEnum.PROPERTY_VIEW_OWN,
            PermissionsEnum.PROPERTY_EDIT_OWN,
            PermissionsEnum.USER_EDIT_NAME_SELF,
            PermissionsEnum.USER_CREATE_CLIENT,
            PermissionsEnum.USER_VIEW_CLIENTS_OWN
    ));

    ROLE_PERMISSIONS.put(User.TipoDeUsuario.CLIENTE, EnumSet.of(
            PermissionsEnum.PROPERTY_VIEW_ALL,
            PermissionsEnum.PROPERTY_VIEW_OWN,
            PermissionsEnum.USER_EDIT_NAME_SELF
    ));
  }

  public boolean hasPermission(User.TipoDeUsuario tipoDeUsuario, PermissionsEnum permission) {
    Set<PermissionsEnum> permissions = ROLE_PERMISSIONS.get(tipoDeUsuario);
    return permissions.contains(permission);
  }
}
