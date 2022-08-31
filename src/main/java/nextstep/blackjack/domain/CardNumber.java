package nextstep.blackjack.domain;

public enum CardNumber {
    ONE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    NINE("8"),
    TEN("9"),
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
}
