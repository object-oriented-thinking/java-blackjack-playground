package blackjack.domain.card;

public class Card {
    private final String name;

    public Card(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Card{}";
    }
}
