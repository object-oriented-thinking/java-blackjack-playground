package nextstep.blackjack.blackjack.domain;

public enum CardNumber {
    ONE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    KING("K"),
    QUEEN("Q"),
    JACK("J");
    private final String cardNumber;

    CardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    String getNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return  cardNumber;
    }
}
