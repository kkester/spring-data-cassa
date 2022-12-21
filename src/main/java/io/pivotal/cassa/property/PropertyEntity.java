package io.pivotal.cassa.property;

import io.pivotal.cassa.board.OwnedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("property")
public class PropertyEntity {
    @PrimaryKey
    private PropertyKey propertyKey;
    private UUID entrepreneurId;
    private OwnedType ownedType;
}
