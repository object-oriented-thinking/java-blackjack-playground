package nextstep.blackjack.blackjack.onecards;

import java.util.LinkedList;

public class OneCards {
    private final LinkedList<Card> cardList;

    public OneCards(LinkedList<Card> cardList) {
        this.cardList = cardList;
    }

    public LinkedList<Card> getCardList() {
        return new LinkedList<>(cardList);
    }

    public Card pollCard() {
        return cardList.pollFirst();
    }
}
