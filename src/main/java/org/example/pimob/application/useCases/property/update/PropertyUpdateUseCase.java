package org.example.pimob.application.useCases.property.update;

import org.example.pimob.application.useCases.property.PropertyValidator;
import org.example.pimob.communication.response.property.PropertyRequest;
import org.example.pimob.exception.property.PropertyNotFoundException;
import org.example.pimob.exception.user.UserNotFoundException;
import org.example.pimob.infrastructure.repositories.PropertyRepository;
import org.example.pimob.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyUpdateUseCase implements IPropertyUpdateUseCase {

  private final PropertyRepository propertyRepository;
  private final UserRepository userRepository;

  public PropertyUpdateUseCase(PropertyRepository propertyRepository, UserRepository userRepository) {
    this.propertyRepository = propertyRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void execute(PropertyRequest request, Long propertyId) {
    var propertyCurrent = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFoundException("Imóvel não encontrado."));

    var user = userRepository.findById(request.criadoPor()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

    var propertyValidator = new PropertyValidator(propertyRepository);
    propertyValidator.validate(request, user);

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
    propertyCurrent.setCriadoPor(user.getId());
    propertyCurrent.setCriadoPara(request.cadastradoPara());
    propertyCurrent.setTelefone(request.telefone());
    propertyCurrent.setNomeDeContato(request.nomeDeContato());
    propertyCurrent.setTipoDeImovel(request.tipoDeImovel());
    System.out.println(propertyCurrent);

    this.propertyRepository.save(propertyCurrent);

  }
}
