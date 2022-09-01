package nextstep.blackjack.blackjack.member;

import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;

import java.util.Arrays;

public class Dealer {
    private static final int CRITERION = 16;
    private final Cards cards;

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