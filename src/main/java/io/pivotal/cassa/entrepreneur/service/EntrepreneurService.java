package io.pivotal.cassa.entrepreneur.service;

import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.IEntrepreneurCommand;
import io.pivotal.cassa.entrepreneur.IEntrepreneurRetriever;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EntrepreneurService implements IEntrepreneurRetriever, IEntrepreneurCommand {

    private final EntrepreneurRepository entrepreneurRepository;
    private final EntrepreneurConverter entrepreneurConverter;

    @Override
    public List<Entrepreneur> retrieveEntrepreneurs(UUID monopolyEntityId) {
        return entrepreneurRepository.findAllByMonopolyId(monopolyEntityId).stream()
                .map(entrepreneurConverter::toEntrepreneur)
                .toList();
    }

    @Override
    public Optional<Entrepreneur> findById(UUID playerId) {
        return entrepreneurRepository.findById(playerId)
                .map(entrepreneurConverter::toEntrepreneur);
    }

    @Override
    public void update(Entrepreneur entrepreneur) {
        EntrepreneurEntity entrepreneurEntity = entrepreneurRepository.findById(entrepreneur.getId()).orElseThrow();
        entrepreneurEntity.setFunds(entrepreneur.getFunds());
        entrepreneurEntity.setSquareId(entrepreneur.getSquareId());
        entrepreneurEntity.setMessage(entrepreneur.getMessage());
        entrepreneurRepository.save(entrepreneurEntity);
    }
}
