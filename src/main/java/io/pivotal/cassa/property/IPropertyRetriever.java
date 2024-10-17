package io.pivotal.cassa.property;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPropertyRetriever {
    List<Property> findAllByEntrepreneurId(UUID playerId);
    Optional<Property> findById(UUID monopolyId, Integer squareId);
}
