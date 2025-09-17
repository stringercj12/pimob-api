package org.example.pimob.application.useCases.property.getById;

import org.example.pimob.communication.response.property.PropertyResponse;
import org.example.pimob.exception.property.PropertyNotFoundException;
import org.example.pimob.infrastructure.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyGetByIdUseCase implements IPropertyGetByIdUseCase {

  private final PropertyRepository propertyRepository;

  public PropertyGetByIdUseCase(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }


  @Override
  public PropertyResponse execute(Long id) {
    var property = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Imóvel não encontrado"));

    return new PropertyResponse(
            property.getId(),
            property.getTitulo(),
            property.getDescricao(),
            property.getLogradouro(),
            property.getCidade(),
            property.getNumero(),
            property.getComplemento(),
            property.getBairro(),
            property.getCep(),
            property.getEstado(),
            property.getPrecoDeVendaOuAluguel(),
            property.getLatitude(),
            property.getLongitude(),
            property.getNumeroDeQuartos(),
            property.getCriadoPor().getId(),
            property.getCriadoPara().getId(),
            property.getTelefone(),
            property.getNomeDeContato(),
            property.getVagasNaGaragem(),
            property.getEstaBloqueado(),
            property.getTipoDeImovel(),
            property.getStatusDoImovel()
    );
  }
}
