package nextstep.blackjack.blackjack.member;

import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;

import static nextstep.blackjack.blackjack.fixture.OneCardFixture.CARD_LIST;
import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsTest {

    OneCards 카드덱;


    @BeforeEach
    void setUp() {
        카드덱 = new OneCards(new LinkedList<>(CARD_LIST));
    }

    private Cards getCards() {
        return new Cards(카드덱.pollCard(), 카드덱.pollCard());
    }

    @Test
    @DisplayName("게임에 참여자들이 입장한다.")
    void participants_create() {
        Assertions.assertDoesNotThrow(
            () -> new Participants(Arrays.asList(
                new Participant("tis", BigDecimal.TEN, getCards()),
                new Participant("me", BigDecimal.valueOf(20_000), getCards()),
                new Participant("yolo", BigDecimal.valueOf(30_000_000), getCards())
            )
            )
        );
    }

    @Test
    @DisplayName("참여자들에게 카드를 분배한다.")
    void participants_distribute() {
        // given
        int size = 카드덱.getCardList().size();

        Participants participants = new Participants(Arrays.asList(
            new Participant("tis", BigDecimal.TEN, getCards()),
            new Participant("me", BigDecimal.valueOf(20_000), getCards()),
            new Participant("yolo", BigDecimal.valueOf(30_000_000), getCards())
        ));

        // when
        participants.distribute(카드덱);

        // then
        assertThat(카드덱.getCardList()).hasSize(size - 9);
    }
}
