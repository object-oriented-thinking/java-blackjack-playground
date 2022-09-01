package nextstep.blackjack.blackjack.onecards;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OneCardsGenerator {
    public static OneCards generateOneCards() {
        List<Card> cards = getCards();
        shuffle(cards);
        return new OneCards((LinkedList<Card>) cards);
    }

    private static void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    private static List<Card> getCards() {
        List<Card> cards = new LinkedList<>();
        Arrays.stream(CardType.values())
            .forEach(
                cardType -> Arrays
                    .stream(CardNumber.values())
                    .forEach(cardNumber -> cards.add(new Card(cardType, cardNumber)))
            );
        return cards;
    }
}
