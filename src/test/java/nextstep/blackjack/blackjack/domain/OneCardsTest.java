package nextstep.blackjack.blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class OneCardsTest {
    private static final Card 첫_번째_카드 = new Card(CardType.CLOVER, CardNumber.ACE);
    private static final Card 두_번째_카드 = new Card(CardType.CLOVER, CardNumber.TWO);
    private static final Card 세_번째_카드 = new Card(CardType.CLOVER, CardNumber.THREE);
    private static final Card 네_번쨰_카드 = new Card(CardType.CLOVER, CardNumber.FOUR);
    private static final List<Card> CARD_LIST = Arrays.asList(첫_번째_카드, 두_번째_카드, 세_번째_카드, 네_번쨰_카드);
    private final OneCards oneCards = new OneCards(new LinkedList<>(CARD_LIST));

    @Test
    @DisplayName("카드덱에서 카드를 뽑으면 카드덱에서 한 장 사라진다.")
    void getCard() {
        // given
        int 카드덱_사이즈 = oneCards.getCardList().size();

        // when
        oneCards.pollCard();

        // then
        Assertions.assertThat(oneCards.getCardList().size()).isEqualTo(카드덱_사이즈 - 1);
    }

    @Test
    @DisplayName("카드덱은 추가된 순서대로 받을 수 있다.")
    void getFirstCard() {
        // when
        Card card = oneCards.pollCard();

        // then
        Assertions.assertThat(card).isEqualTo(첫_번째_카드);
    }
}
