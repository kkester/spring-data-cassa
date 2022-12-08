package io.pivotal.cassa.entrepreneur;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Entrepreneur {
    @PrimaryKey
    private UUID id;
    private String name;
    private TokenType tokenType;
    private Double funds;
}
