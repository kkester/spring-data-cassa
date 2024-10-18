package io.pivotal.cassa.monopoly.executors;

import io.pivotal.cassa.board.OwnedType;
import io.pivotal.cassa.board.db.SquareRepository;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurEntity;
import io.pivotal.cassa.entrepreneur.db.EntrepreneurRepository;
import io.pivotal.cassa.monopoly.db.MonopolyRepository;
import io.pivotal.cassa.property.db.PropertyEntity;
import io.pivotal.cassa.property.db.PropertyKey;
import io.pivotal.cassa.property.db.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MonopolyExecutorTest {

    @Autowired
    MonopolyExecutor monopolyExecutor;

    @MockBean
    EntrepreneurRepository entrepreneurRepository;

    @MockBean
    SquareRepository squareRepository;

    @MockBean
    PropertyRepository propertyRepository;

    @MockBean
    MonopolyRepository monopolyRepository;

    @Test
    void rollDice() {
        UUID monopolyId = UUID.randomUUID();
        UUID player1Id = UUID.randomUUID();
        EntrepreneurEntity player1 = EntrepreneurEntity.builder()
                .id(player1Id)
                .squareId(1)
                .funds(500)
                .build();
        when(entrepreneurRepository.findById(player1Id)).thenReturn(Optional.of(player1));
        when(entrepreneurRepository.findAllByMonopolyId(monopolyId)).thenReturn(List.of(player1));

        UUID player2Id = UUID.randomUUID();
        EntrepreneurEntity player2 = EntrepreneurEntity.builder()
                .id(player2Id)
                .squareId(2)
                .funds(1000)
                .build();
        when(entrepreneurRepository.findById(player2Id)).thenReturn(Optional.of(player2));
        when(squareRepository.findById(anyInt())).thenReturn(SquareRepository.BOARDWALK);

        PropertyKey boardwalkPropertyKey = PropertyKey.builder().squareId(SquareRepository.BOARDWALK.getId()).monopolyId(monopolyId).build();
        PropertyEntity boardwalkPropertyEntity = PropertyEntity.builder()
                .propertyKey(boardwalkPropertyKey)
                .entrepreneurId(player2Id)
                .ownedType(OwnedType.HOTEL)
                .build();
        when(propertyRepository.findById(boardwalkPropertyKey)).thenReturn(Optional.of(boardwalkPropertyEntity));
        PropertyKey ncPropertyKey = PropertyKey.builder().squareId(SquareRepository.NC_AVE.getId()).monopolyId(monopolyId).build();
        PropertyEntity ncPropertyEntity = PropertyEntity.builder()
                .propertyKey(ncPropertyKey)
                .entrepreneurId(player1Id)
                .ownedType(OwnedType.HOTEL)
                .build();
        when(propertyRepository.findById(ncPropertyKey)).thenReturn(Optional.of(ncPropertyEntity));
        when(propertyRepository.findAllByEntrepreneurId(player1Id)).thenReturn(List.of(ncPropertyEntity));

        monopolyExecutor.rollDice(monopolyId);

        assertThat(player1.getFunds()).isEqualTo(-150);
        assertThat(player2.getFunds()).isEqualTo(3000);
        verify(propertyRepository).deleteById(ncPropertyKey);
    }
}