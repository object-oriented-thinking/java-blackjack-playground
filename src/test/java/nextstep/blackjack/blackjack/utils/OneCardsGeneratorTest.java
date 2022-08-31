package nextstep.blackjack.blackjack.utils;

import nextstep.blackjack.blackjack.domain.OneCards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OneCardsGeneratorTest {
    private static final int ONE_CARDS_SIZE = 52;

    @Test
    @DisplayName("카드덱에는 52 종류의 카드가 저장되어 있다.")
    void getOneCardsSize() {
        OneCards oneCards = OneCardsGenerator.generateOneCards();
        Assertions.assertThat(oneCards.getCardList()).hasSize(ONE_CARDS_SIZE);
    }
}
