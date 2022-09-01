package nextstep.blackjack.blackjack.onecards;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static nextstep.blackjack.blackjack.fixture.OneCardFixture.CARD_LIST;
import static nextstep.blackjack.blackjack.fixture.OneCardFixture.첫_번째_카드;

class OneCardsTest {

    OneCards 카드덱;

    @BeforeEach
    void setUp() {
        카드덱 = new OneCards(new LinkedList<>(CARD_LIST));
    }

    @Test
    @DisplayName("카드덱에서 카드를 뽑으면 카드덱에서 한 장 사라진다.")
    void getCard() {
        // given
        int 카드덱_사이즈 = 카드덱.getCardList().size();

        // when
        카드덱.pollCard();

        // then
        Assertions.assertThat(카드덱.getCardList().size()).isEqualTo(카드덱_사이즈 - 1);
    }

    @Test
    @DisplayName("카드덱은 추가된 순서대로 받을 수 있다.")
    void getFirstCard() {
        // when
        Card card = 카드덱.pollCard();

        // then
        Assertions.assertThat(card).isEqualTo(첫_번째_카드);
    }
}
