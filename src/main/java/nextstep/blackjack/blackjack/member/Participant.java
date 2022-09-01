package nextstep.blackjack.blackjack.member;

import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;

import java.math.BigDecimal;

public class Participant {
    private final String username;
    private final BigDecimal bettingMoney;
    private Cards cards;

    public Participant(String username, BigDecimal bettingMoney, Cards cards) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (bettingMoney.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }

        this.username = username;
        this.bettingMoney = bettingMoney;
        this.cards = cards;
    }

    public Participant(String username, BigDecimal bettingMoney, OneCards oneCards) {
        this(username, bettingMoney, new Cards(oneCards));
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney;
    }

    public Cards getCards() {
        return cards;
    }

    public void updateCards(Cards cards) {
        this.cards = cards;
    }
}