package nextstep.blackjack.blackjack.domain;

import java.util.Arrays;
import java.util.List;

public class Cards {
    private final List<Card> card;

    public Cards(Card one, Card two) {
        this.card = Arrays.asList(one, two);
    }
}
