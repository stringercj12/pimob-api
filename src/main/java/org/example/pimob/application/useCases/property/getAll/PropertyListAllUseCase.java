package org.example.pimob.application.useCases.property.getAll;

import org.example.pimob.communication.response.property.PropertyResponse;
import org.example.pimob.infrastructure.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyListAllUseCase implements IPropertyListAllUseCase {

    private final PropertyRepository propertyRepository;

    public PropertyListAllUseCase(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<PropertyResponse> execute() {
        var properties = this.propertyRepository.findAll();

        return properties.stream().map(property -> new PropertyResponse(
                property.getId(),
                property.getTitulo(),
                property.getDescricao(),
                property.getEndereco(),
                property.getCidade(),
                property.getNumero(),
                property.getBairro(),
                property.getCep(),
                property.getEstado(),
                property.getPrecoDeVendaOuAluguel(),
                property.getLatitude(),
                property.getLongitude(),
                property.getNumeroDeQuartos(),
                property.getCriadoPor(),
                property.getCriadoPara(),
                property.getTelefone(),
                property.getNomeDeContato(),
                property.getVagasNaGaragem(),
                property.getEstaBloqueado(),
                property.getTipoDeImovel(),
                property.getStatus()
        )).toList();
    }
}
