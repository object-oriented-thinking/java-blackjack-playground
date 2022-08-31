package nextstep.blackjack.domain;

public enum CardType {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드");

    private final String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }

    String getType() {
        return cardType;
    }
}
