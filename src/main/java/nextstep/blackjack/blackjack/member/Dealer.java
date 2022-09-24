package nextstep.blackjack.blackjack.member;

import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;

import java.util.Arrays;

public class Dealer {
    private static final int CRITERION = 16;
    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    /**
     * 딜러는 카드값이 CRITERION을 넘지 않으면 카드를 한 장 더 뽑는다.*
     * @param oneCards
     */
    public Dealer(OneCards oneCards) {
        Cards cards = new Cards(Arrays.asList(oneCards.pollCard(), oneCards.pollCard()));

        while (condition(cards)) {
            cards = cards.putCard(oneCards.pollCard());
        }

        this.cards = cards;
    }

    private boolean condition(Cards cards) {
        return cards.sumAll() <= CRITERION;
    }

    public Cards getCards() {
        return cards;
    }
}
