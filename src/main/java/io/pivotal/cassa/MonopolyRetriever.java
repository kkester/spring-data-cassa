package io.pivotal.cassa;

import io.pivotal.cassa.board.SquareRepository;
import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.EntrepreneurConverter;
import io.pivotal.cassa.entrepreneur.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.EntrepreneurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MonopolyRetriever {
    private final MonopolyRepository monopolyRepository;
    private final EntrepreneurRepository entrepreneurRepository;
    private final EntrepreneurConverter entrepreneurConverter;

    public Monopoly get(UUID monopolyId) {
        return monopolyRepository.findById(monopolyId)
            .map(this::convert)
            .orElseThrow(RuntimeException::new);
    }

    private Monopoly convert(MonopolyEntity monopolyEntity) {
        List<Entrepreneur> entrepreneurs = entrepreneurRepository.findAllByMonopolyId(monopolyEntity.getId()).stream()
            .map(entrepreneurConverter::toEntrepreneur)
            .collect(Collectors.toList());
        return Monopoly.builder()
            .id(monopolyEntity.getId())
            .pot(monopolyEntity.getPot())
            .players(entrepreneurs)
            .build();
    }
}
