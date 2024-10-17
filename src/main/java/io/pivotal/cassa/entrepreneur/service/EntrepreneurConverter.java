package io.pivotal.cassa.entrepreneur.service;

import io.pivotal.cassa.entrepreneur.Entrepreneur;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurEntity;
import org.springframework.stereotype.Component;

@Component
public class EntrepreneurConverter {
    public Entrepreneur toEntrepreneur(EntrepreneurEntity entrepreneurEntity) {
        return Entrepreneur.builder()
                .id(entrepreneurEntity.getId())
                .name(entrepreneurEntity.getName())
                .tokenType(entrepreneurEntity.getTokenType())
                .funds(entrepreneurEntity.getFunds())
                .human(entrepreneurEntity.isHuman())
                .squareId(entrepreneurEntity.getSquareId())
                .message(entrepreneurEntity.getMessage())
                .build();
    }
}
