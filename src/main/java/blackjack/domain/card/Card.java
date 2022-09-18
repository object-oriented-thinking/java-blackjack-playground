package blackjack.domain.card;

public class Card {
    private final String name;
    private final Character number;

    public Card(String name, Character number) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() throws Exception {
        if(number>= '2' && number <= '9') {
            return number-'0';
        }
        if(number == 'K' || number == 'Q' || number == 'J') {
            return 10;
        }
        if(number == 'A') {
            return 1; //if card's total value is less than 11, then add 10 additionally.
        }
        throw new Exception("can't return value of number");
    }

    @Override
    public String toString() {
        return number + name;
    }
}
