package io.pivotal.cassa.entrepreneur;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEntrepreneurRetriever {
    List<Entrepreneur> retrieveEntrepreneurs(UUID monopolyEntityId);
    Optional<Entrepreneur> findById(UUID playerID);
}
