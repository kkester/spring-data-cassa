package io.pivotal.cassa.property;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyClass
public class PropertyKey implements Serializable {
    @PrimaryKeyColumn
    private UUID monopolyId;
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private int squareId;
}
