package io.pivotal.cassa.entrepreneur;

import io.pivotal.cassa.board.TokenType;

import java.util.UUID;

public interface IEntrepreneurGenerator {
    Entrepreneur create(UUID id, TokenType token, boolean human);
    Entrepreneur createWithAnyTokenExcept(UUID id, TokenType...tokens);
}
