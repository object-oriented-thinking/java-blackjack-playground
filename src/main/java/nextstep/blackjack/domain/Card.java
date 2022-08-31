package nextstep.blackjack.domain;

public class Card {
    private final CardType cardType;
    private final CardNumber cardNumber;

    public Card(CardType cardType, CardNumber cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public String name() {
        return cardNumber.getNumber() + cardType.getType();
    }
}
