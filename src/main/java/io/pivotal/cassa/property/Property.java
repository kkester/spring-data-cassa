package io.pivotal.cassa.property;

import io.pivotal.cassa.board.OwnedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private UUID monopolyId;
    private Integer squareId;
    private UUID entrepreneurId;
    private OwnedType ownedType;
}
