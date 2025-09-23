package org.example.pimob.application.useCases.property.update;

import org.example.pimob.application.useCases.auth.PermissionUseCase;
import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.domain.entities.UserEntity;
import org.example.pimob.domain.enums.PermissionsEnum;
import org.example.pimob.exception.others.BusinessRuleException;
import org.example.pimob.exception.property.PropertyDuplicateException;
import org.example.pimob.exception.property.PropertyNotFoundException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.PropertyRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyUpdateUseCase implements IPropertyUpdateUseCase {

  private final PropertyRepository propertyRepository;
  private final UserRepository userRepository;
  private final PermissionUseCase permissionUseCase;

  public PropertyUpdateUseCase(PropertyRepository propertyRepository, UserRepository userRepository, PermissionUseCase permissionUseCase) {
    this.propertyRepository = propertyRepository;
    this.userRepository = userRepository;
    this.permissionUseCase = permissionUseCase;
  }

  @Override
  public void execute(PropertyRequest request, Long propertyId) {
    var propertyCurrent = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFoundException("Imóvel não encontrado."));

    var currentUser = userRepository.findById(request.criadoPorUserId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
//
//    if (!permissionUseCase.hasPermission(currentUser.getTipoDeUsuario(), PermissionsEnum.PROPERTY_ADD)) {
//      throw new BusinessRuleException("Usuário não tem permissão para adicionar imóveis.");
//    }

    validateDuplicationProperty(request);
    validateLimitToRegister(currentUser);

    propertyCurrent.setTitulo(request.titulo());
    propertyCurrent.setDescricao(request.descricao());
    propertyCurrent.setLogradouro(request.logradouro());
    propertyCurrent.setCidade(request.cidade());
    propertyCurrent.setCep(request.cep());
    propertyCurrent.setNumero(request.numero());
    propertyCurrent.setComplemento(request.complemento());
    propertyCurrent.setBairro(request.bairro());
    propertyCurrent.setEstado(request.estado());
    propertyCurrent.setPrecoDeVendaOuAluguel(request.precoDeVendaOuAluguel());
    propertyCurrent.setLatitude(request.latitude());
    propertyCurrent.setLongitude(request.longitude());
    propertyCurrent.setNumeroDeQuartos(request.numeroDeQuartos());
    propertyCurrent.setVagasNaGaragem(request.vagasNaGaragem());
    propertyCurrent.setTelefone(request.telefone());
    propertyCurrent.setNomeDeContato(request.nomeDeContato());
    propertyCurrent.setTipoDeImovel(request.tipoDeImovel());

    this.propertyRepository.save(propertyCurrent);

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
