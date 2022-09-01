package nextstep.blackjack.blackjack.domain;

public class Dealer {
    private static final int CRITERION = 16;
    private final Cards cards;

    public Dealer(OneCards oneCards) {
        Cards cards = new Cards(oneCards.pollCard(), oneCards.pollCard());

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
