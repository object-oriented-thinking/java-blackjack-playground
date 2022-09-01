package nextstep.blackjack.blackjack.onecards;

import nextstep.blackjack.blackjack.onecards.Card;
import nextstep.blackjack.blackjack.onecards.CardNumber;
import nextstep.blackjack.blackjack.onecards.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CardTest {

    @Test
    @DisplayName("카드는 숫자와 타입을 가진다.")
    void card_create() {
        assertDoesNotThrow(
            () -> new Card(CardType.CLOVER, CardNumber.FOUR)
        );
    }

    @Test
    @DisplayName("자신의 정보를 반환한다.")
    void card_getName() {
        CardType clover = CardType.CLOVER;
        CardNumber four = CardNumber.FOUR;

        Card clover_four = new Card(clover, four);
        assertThat(clover_four.name()).isEqualTo(four.number() + clover.getType());
    }
}
