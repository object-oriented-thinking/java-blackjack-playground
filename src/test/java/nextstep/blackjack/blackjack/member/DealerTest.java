package nextstep.blackjack.blackjack.member;

import nextstep.blackjack.blackjack.onecards.OneCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static nextstep.blackjack.blackjack.fixture.OneCardFixture.CARD_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DealerTest {

    OneCards 카드덱;

    @BeforeEach
    void setUp() {
        카드덱 = new OneCards(new LinkedList<>(CARD_LIST));
    }

    @Test
    @DisplayName("딜러는 카드를 받습니다.")
    void dealer_create() {
        assertDoesNotThrow(
            () -> new Dealer(카드덱)
        );
    }

    @Test
    @DisplayName("딜러의 카드가 16이하면 카드를 추가로 받습니다.")
    void dealer_create_add_cards() {
        Dealer dealer = new Dealer(카드덱);
        assertThat(dealer.getCards().sumAll()).isGreaterThan(16);
    }
}
