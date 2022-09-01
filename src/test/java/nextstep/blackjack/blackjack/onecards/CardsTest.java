package nextstep.blackjack.blackjack.onecards;

import nextstep.blackjack.blackjack.onecards.Card;
import nextstep.blackjack.blackjack.onecards.CardNumber;
import nextstep.blackjack.blackjack.onecards.CardType;
import nextstep.blackjack.blackjack.onecards.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CardsTest {
    private static final Card CLOVER_FOUR = new Card(CardType.CLOVER, CardNumber.FOUR);
    private static final Card CLOVER_ACE = new Card(CardType.CLOVER, CardNumber.ACE);
    private static final Card HEART_ACE = new Card(CardType.HEART, CardNumber.ACE);
    private static final Card CLOVER_THREE = new Card(CardType.CLOVER, CardNumber.THREE);
    private static final Card CLOVER_JACK = new Card(CardType.CLOVER, CardNumber.JACK);
    private static final int SEVEN = 7;
    private static final int TWELVE = 12;

    @Test
    @DisplayName("카드 패는 최소 두 장이 들어간다.")
    void cards_atLeast_two() {
        assertDoesNotThrow(
            () -> new Cards(CLOVER_FOUR, CLOVER_THREE)
        );
    }

    @Test
    @DisplayName("카드패의 숫자 합을 출력한다.")
    void sumCards() {
        Cards cards = new Cards(CLOVER_FOUR, CLOVER_THREE);
        assertThat(cards.sumAll()).isEqualTo(SEVEN);
    }

    @Test
    @DisplayName("Ace가 두장이면 12이다.")
    void sumCards_ace_and_ace() {
        Cards cards = new Cards(CLOVER_ACE, HEART_ACE);
        assertThat(cards.sumAll()).isEqualTo(TWELVE);
    }

    @Test
    @DisplayName("에이스와 잭이면 합이 21이다.")
    void sumCards_ace_and_jack() {
        Cards cards = new Cards(CLOVER_ACE, CLOVER_JACK);
        assertThat(cards.sumAll()).isEqualTo(21);
    }

    @Test
    @DisplayName("에이스가 두 장이고 잭이 한 장이면 12이다.")
    void sumCards_ace_and_ace_and_jack() {
        Cards cards = new Cards(CLOVER_ACE, CLOVER_JACK);

        Cards newCards = cards.putCard(HEART_ACE);

        assertThat(newCards.sumAll()).isEqualTo(12);
    }

    @Test
    @DisplayName("합이 21이 넘으면 더 이상 받을 수 없다.")
    void cards_putCard_notSumAllOver21() {

    }
}
