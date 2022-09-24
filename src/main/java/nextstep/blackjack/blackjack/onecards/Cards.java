package nextstep.blackjack.blackjack.onecards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Cards {
    private static final int DEFAULT_SIZE = 2;
    private static final int MAX_SCORE = 21;
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public Cards(OneCards oneCards) {
        this(Arrays.asList(oneCards.pollCard(), oneCards.pollCard()));
    }

    private List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int sumAll() {
        final int aceCount = (int) getCards().stream()
            .filter(card -> card.getCardNumber().isAce())
            .count();

        final int value = getCards().stream()
            .mapToInt(card -> card.getCardNumber().score())
            .sum();

        if (aceCount > 0 && value <= 11) {
            return value + 10;
        }
        return value;
    }

    public Cards putCard(final Card card) {
        List<Card> cards = getCards();
        cards.add(getCard(card));
        return new Cards(cards);
    }

    private Card getCard(Card card) {
        return Optional.ofNullable(card)
            .orElseThrow(() -> new IllegalArgumentException("Card는 Null일 수 없습니다."));
    }

    public boolean isCountTwo() {
        return cards.size() == DEFAULT_SIZE;
    }

    public boolean isOver21() {
        return sumAll() > MAX_SCORE;
    }

    public boolean isNotOver21() {
        return sumAll() < MAX_SCORE;
    }

    public boolean is21() {
        return sumAll() == MAX_SCORE;
    }
}
