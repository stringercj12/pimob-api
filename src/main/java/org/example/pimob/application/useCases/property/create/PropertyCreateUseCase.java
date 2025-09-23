package org.example.pimob.application.useCases.property.create;

import org.example.pimob.application.useCases.auth.PermissionUseCase;
import org.example.pimob.communication.response.property.PropertyRegisterResponse;
import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.domain.entities.Property;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.exception.others.BusinessRuleException;
import org.example.pimob.exception.property.PropertyDuplicateException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.PropertyRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyCreateUseCase implements IPropertyCreateUseCase {

  private final PropertyRepository propertyRepository;
  private final UserRepository userRepository;
  private final PermissionUseCase permissionUseCase;

  public PropertyCreateUseCase(PropertyRepository propertyRepository, UserRepository userRepository, PermissionUseCase permissionUseCase) {
    this.propertyRepository = propertyRepository;
    this.userRepository = userRepository;
    this.permissionUseCase = permissionUseCase;
  }

  @Override
  public PropertyRegisterResponse execute(PropertyRequest request) {

    var currentUser = userRepository.findById(request.criadoPorUserId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
//
//    if (!permissionUseCase.hasPermission(currentUser.getTipoDeUsuario(), PermissionsEnum.PROPERTY_ADD)) {
//      throw new BusinessRuleException("Usuário não tem permissão para adicionar imóveis.");
//    }

    validateDuplicationProperty(request);
    validateLimitToRegister(currentUser);

    Property property = Property.createNew(
            request.titulo(),
            request.descricao(),
            request.logradouro(),
            request.cidade(),
            request.cep(),
            request.numero(),
            request.complemento(),
            request.bairro(),
            request.estado(),
            request.precoDeVendaOuAluguel(),
            request.latitude(),
            request.longitude(),
            request.numeroDeQuartos(),
            request.vagasNaGaragem(),
            currentUser,
            null,
            request.telefone(),
            request.nomeDeContato(),
            request.tipoDeImovel()
    );


    if (request.criadoParaUserId() != null) {

      UserEntity criadoParaUser = new UserEntity();
      criadoParaUser.setId(request.criadoParaUserId());
      criadoParaUser.setNome(request.nomeDeContato());
      criadoParaUser.setTelefone(request.telefone());
//      criadoParaUser.setTipoDeUsuario(UserEntity.TipoDeUsuario.CLIENTE);

//      if (currentUser.getTipoDeUsuario() == UserEntity.TipoDeUsuario.CORRETOR &&
//              !permissionUseCase.hasPermission(currentUser.getTipoDeUsuario(), PermissionsEnum.USER_CREATE_CLIENT)) {
//        throw new BusinessRuleException("Corretor não tem permissão para cadastrar imóvel para este cliente.");
//      }

      property.setCriadoPara(criadoParaUser);
    }

    var propertyCreated = this.propertyRepository.save(property);
    return new PropertyRegisterResponse(propertyCreated.getId());
  }

  private void validateDuplicationProperty(PropertyRequest request) {
    boolean exists = propertyRepository.existsByLogradouroAndNumeroAndComplementoAndCep(
            request.logradouro(), request.numero(), request.complemento(), request.cep()
    );
    if (exists) {
      throw new PropertyDuplicateException("Imóvel já cadastrado.");
    }
  }

  private void validateLimitToRegister(UserEntity user) {
//    if (user.getTipoDeUsuario() == UserEntity.TipoDeUsuario.CLIENTE) {
//      long totalImoveis = propertyRepository.findByCriadoPor(user).size();
//
//      if (totalImoveis >= 10) {
//        throw new BusinessRuleException("Limite de 10 imóveis por ciente antigido.");
//      }
//    }
  }
}
