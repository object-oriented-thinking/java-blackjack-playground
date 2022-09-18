package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardFactory {
    private final List<Card> cardPool;

    public CardFactory() {
        this.cardPool = initCardPool();
    }

    protected List<Card> initCardPool() {
        List<Card> cardList = new ArrayList<>();
        List<String> figures = Arrays.asList("스페이드", "클로버", "하트", "다이아몬드");
        List<Character> numbers = Arrays.asList('2','3','4','5','6','7','8','9','K','Q','J','A');
        for (String figure:figures) {
            for (Character number:numbers) {
                cardList.add(new Card(figure, number));
            }
        }
        return cardList;
    }

    public Card selectCard(){
        int index = new Random().nextInt(this.cardPool.size());
        Card card = cardPool.get(index);
        cardPool.remove(index);
        return card;
    }
}
