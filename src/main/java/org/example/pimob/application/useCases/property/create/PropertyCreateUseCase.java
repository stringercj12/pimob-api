package org.example.pimob.application.useCases.property.create;

import org.example.pimob.communication.response.property.PropertyRegisterResponse;
import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.communication.response.property.PropertyResponse;
import org.example.pimob.domain.entities.Property;
import org.example.pimob.domain.entities.User;
import org.example.pimob.exception.*;
import org.example.pimob.infrastructure.repositories.PropertyRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.beans.PropertyEditor;

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

    Property property = new Property();

    property.setTitulo(request.titulo());
    property.setDescricao(request.descricao());
    property.setNumeroDeQuartos(request.numeroDeQuartos());
    property.setPrecoDeVendaOuAluguel(request.precoDeVendaOuAluguel());
    property.setVagasNaGaragem(request.vagasNaGaragem());

    property.setLatitude(request.latitude());
    property.setLongitude(request.longitude());
    property.setEndereco(request.endereco());
    property.setNumero(request.numero());
    property.setEstado(request.estado());
    property.setCidade(request.cidade());
    property.setBairro(request.bairro());
    property.setCep(request.cep());

    property.setCriadoPor(user.getId());
    property.setCriadoPara(request.cadastradoPara());
    property.setTelefone(request.telefone());
    property.setNomeDeContato(request.nomeDeContato());

    property.setStatus(Property.StatusImovel.DISPONIVEL);
    property.setTipoDeImovel(request.tipoDeImovel());
    property.setEstaBloqueado(false);

    var propertyCreated = this.propertyRepository.save(property);

    return new PropertyRegisterResponse(propertyCreated.getId());
  }

  private void validate(PropertyRequest request, User user) {
    validatePermissions(user);
    validateDuplicationProperty(request);
    validateFieldsRequired(request);
    validateLimitToRegister(user);
  }

  private void validatePermissions(User user) {
    if (!user.getAtivo()) {
      throw new ForbiddenException("Usuário não tem permissão para cadastrar imóveis.");
    }
  }

  private void validateDuplicationProperty(PropertyRequest request) {
    boolean exists = propertyRepository.existsByEnderecoAndNumeroAndCep(
            request.endereco(), request.numero(), request.cep()
    );
    if (exists) {
      throw new DuplicatePropertyException("Imóvel já cadastrado.");
    }
  }

  private void validateFieldsRequired(PropertyRequest request) {
    if (request.descricao().isBlank()) {
      throw new ValidationException("Descrição é obrigatória.");
    }

    if (request.numero().isBlank()) {
      throw new ValidationException("O número do endereço é obrigatória.");
    }

    if (request.endereco().isBlank()) {
      throw new ValidationException("Endereço é obrigatória.");
    }

    if (request.cep().isBlank()) {
      throw new ValidationException("CEP é obrigatória.");
    }

    if (request.telefone().isBlank()) {
      throw new ValidationException("Telefone é obrigatória.");
    }

    if ((request.statusDoImovel() == Property.StatusImovel.VENDA || request.statusDoImovel() == Property.StatusImovel.ALUGAR) && request.precoDeVendaOuAluguel() == null) {
      throw new ValidationException("Imóveis à venda precisam de um preço.");
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
