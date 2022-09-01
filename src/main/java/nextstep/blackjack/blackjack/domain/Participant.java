package nextstep.blackjack.blackjack.domain;

import nextstep.blackjack.blackjack.onecards.Cards;

import java.math.BigDecimal;

public class Participant {
    private final String username;
    private final BigDecimal bettingMoney;
    private final Cards cards;

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

    public String getUsername() {
        return username;
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney;
    }

    public Cards getCards() {
        return cards;
    }
}
