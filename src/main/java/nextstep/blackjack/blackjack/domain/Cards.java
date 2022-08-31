package nextstep.blackjack.blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public Cards(Card one, Card two) {
        this.cards = Arrays.asList(one, two);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int sumAll() {
        final int aceCount = (int) getCards().stream().filter(card -> card.getCardNumber().equals(CardNumber.ACE)).count();
        final int value = getCards().stream().filter(card -> !card.getCardNumber().equals(CardNumber.ACE))
            .mapToInt(card -> card.getCardNumber().score())
            .sum();

        if (aceCount > 0 && value + aceCount <= 11) {
            return value + aceCount + 10;
        }

        return value + aceCount;
    }

    public Cards putCard(Card card) {
        List<Card> cards = getCards();
        cards.add(
            Optional.ofNullable(card)
                .orElseThrow(() -> new IllegalArgumentException("Card는 Null일 수 없습니다."))
        );
        return new Cards(cards);
    }
}
