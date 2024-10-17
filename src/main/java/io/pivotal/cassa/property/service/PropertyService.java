package io.pivotal.cassa.property.service;

import io.pivotal.cassa.property.IPropertyCommand;
import io.pivotal.cassa.property.IPropertyRetriever;
import io.pivotal.cassa.property.Property;
import io.pivotal.cassa.property.db.PropertyEntity;
import io.pivotal.cassa.property.db.PropertyKey;
import io.pivotal.cassa.property.db.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PropertyService implements IPropertyRetriever, IPropertyCommand {

    private final PropertyRepository propertyRepository;

    @Override
    public List<Property> findAllByEntrepreneurId(UUID playerId) {
        return propertyRepository.findAllByEntrepreneurId(playerId)
                .stream()
                .map(PropertyService::toProperty)
                .toList();
    }

    @Override
    public Optional<Property> findById(UUID monopolyId, Integer squareId) {
        PropertyKey propertyKey = PropertyKey.builder()
                .squareId(squareId)
                .monopolyId(monopolyId)
                .build();
        return propertyRepository.findById(propertyKey)
                .map(PropertyService::toProperty);
    }

    private static Property toProperty(PropertyEntity propertyEntity) {
        PropertyKey propertyKey = propertyEntity.getPropertyKey();
        return Property.builder()
                .squareId(propertyKey.getSquareId())
                .monopolyId(propertyKey.getMonopolyId())
                .entrepreneurId(propertyEntity.getEntrepreneurId())
                .ownedType(propertyEntity.getOwnedType())
                .build();
    }

    @Override
    public void delete(Property property) {
        PropertyKey propertyKey = PropertyKey.builder()
                .squareId(property.getSquareId())
                .monopolyId(property.getMonopolyId())
                .build();
        propertyRepository.deleteById(propertyKey);
    }

    @Override
    public void create(Property property) {
        PropertyKey propertyKey = PropertyKey.builder()
                .squareId(property.getSquareId())
                .monopolyId(property.getMonopolyId())
                .build();
        propertyRepository.save(PropertyEntity.builder()
                .propertyKey(propertyKey)
                .ownedType(property.getOwnedType())
                .entrepreneurId(property.getEntrepreneurId())
                .build());
    }

    @Override
    public void update(Property property) {
        PropertyKey propertyKey = PropertyKey.builder()
                .squareId(property.getSquareId())
                .monopolyId(property.getMonopolyId())
                .build();
        propertyRepository.findById(propertyKey).ifPresent(propertyEntity -> {
            propertyEntity.setOwnedType(property.getOwnedType());
            propertyEntity.setEntrepreneurId(property.getEntrepreneurId());
            propertyRepository.save(propertyEntity);
        });
    }
}
