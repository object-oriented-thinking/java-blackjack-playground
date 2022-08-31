package nextstep.blackjack.blackjack.domain;

import java.util.Objects;

public class Card {
    private final CardType cardType;
    private final CardNumber cardNumber;

    public Card(CardType cardType, CardNumber cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public String name() {
        return cardNumber.getNumber() + cardType.getType();
    }

    @Override
    public String toString() {
        return cardNumber.getNumber() + cardType.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardType == card.cardType && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardType, cardNumber);
    }
}
