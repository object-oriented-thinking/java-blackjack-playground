package nextstep.blackjack.blackjack.domain;

import nextstep.blackjack.blackjack.domain.Card;
import nextstep.blackjack.blackjack.domain.CardNumber;
import nextstep.blackjack.blackjack.domain.CardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드는 숫자와 타입을 가진다.")
    void card_create() {
        Assertions.assertDoesNotThrow(
            () -> new Card(CardType.CLOVER, CardNumber.FOUR)
        );
    }

    @Test
    @DisplayName("자신의 정보를 반환한다.")
    void card_getName() {
        CardType clover = CardType.CLOVER;
        CardNumber four = CardNumber.FOUR;

        Card clover_four = new Card(clover, four);
        org.assertj.core.api.Assertions.assertThat(clover_four.name()).isEqualTo(four.getNumber() + clover.getType());
    }
}
