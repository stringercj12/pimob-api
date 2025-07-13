package org.example.pimob.application.useCases.property.create;

import org.example.pimob.communication.response.property.PropertyRegisterResponse;
import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.domain.entities.Property;
import org.example.pimob.domain.entities.User;
import org.example.pimob.exception.others.BusinessRuleException;
import org.example.pimob.exception.property.PropertyDuplicateException;
import org.example.pimob.exception.others.ForbiddenException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.PropertyRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyCreateUseCase implements IPropertyCreateUseCase {

  private final PropertyRepository propertyRepository;
  private final UserRepository userRepository;

  public PropertyCreateUseCase(PropertyRepository propertyRepository, UserRepository userRepository) {
    this.propertyRepository = propertyRepository;
    this.userRepository = userRepository;
  }

  @Override
  public PropertyRegisterResponse execute(PropertyRequest request) {

    var user = userRepository.findById(request.criadoPor()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

    validate(request, user);

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
            user.getId(),
            request.cadastradoPara(),
            request.telefone(),
            request.nomeDeContato(),
            request.tipoDeImovel()
    );

    var propertyCreated = this.propertyRepository.save(property);

    return new PropertyRegisterResponse(propertyCreated.getId());
  }

  private void validate(PropertyRequest request, User user) {
    validatePermissions(user);
    validateDuplicationProperty(request);
    validateLimitToRegister(user);
  }

  private void validatePermissions(User user) {
    if (!user.getAtivo()) {
      throw new ForbiddenException("Usuário não tem permissão para cadastrar imóveis.");
    }
  }

  private void validateDuplicationProperty(PropertyRequest request) {
    boolean exists = propertyRepository.existsByLogradouroAndNumeroAndComplementoAndCep(
            request.logradouro(), request.numero(), request.complemento(), request.cep()
    );
    if (exists) {
      throw new PropertyDuplicateException("Imóvel já cadastrado.");
    }
  }

  private void validateLimitToRegister(User user) {
    if (user.getTipoDeUsuario() == User.TipoDeUsuario.CLIENTE) {
      long totalImoveis = propertyRepository.countByCriadoPor(user.getId());

      if (totalImoveis >= 10) {
        throw new BusinessRuleException("Limite de 10 imóveis por ciente antigido.");
      }
    }
  }
}
