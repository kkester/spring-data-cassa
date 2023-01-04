package io.pivotal.cassa.entrepreneur;

import org.springframework.stereotype.Component;

@Component
public class EntrepreneurConverter {

    public Entrepreneur toEntrepreneur(EntrepreneurEntity entrepreneurEntity) {
        return Entrepreneur.builder()
            .name(entrepreneurEntity.getName())
            .tokenType(entrepreneurEntity.getTokenType())
            .funds(entrepreneurEntity.getFunds())
            .human(entrepreneurEntity.isHuman())
            .message(entrepreneurEntity.getMessage())
            .build();
    }
}
