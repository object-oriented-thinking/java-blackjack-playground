package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cardList;
    private int total;

    public Cards() {
        this.cardList = new ArrayList<>();
        this.total = 0;
    }

    public String getAllCards() {
        return cardList.toString();
    }

    public void getMoreCard(CardFactory cardFactory) throws Exception {
        Card card = cardFactory.selectCard();
        cardList.add(card);
        Integer value = card.getNumber();
        if(total < 11 && value==1){
            total += 10; //handle if a number of card is 'A'
        }
        total += card.getNumber();
    }

    public int getTotal(){
        return total;
    }

    public int getSize() {
        return this.cardList.size();
    }
}
