package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    CardFactory cardFactory;
    @BeforeEach
    public void setUp() {
        cardFactory = new CardFactory();
    }

    @Test
    public void testCardFactory(){
        List<Card> cardList = cardFactory.initCardPool();
        assertThat(cardList.size()).isEqualTo(12 * 4);
    }

    @Test
    void testCards() throws Exception {
        Cards cards = new Cards();
        cards.getMoreCard(cardFactory);
        cards.getMoreCard(cardFactory);
        System.out.println(cards.getAllCards());
    }

    @Test
    public void testCardNumber() throws Exception {
        Card card = new Card("", 'Q');
        Card card2 = new Card("", '3');

        assertThat(card.getNumber()).isEqualTo(10);
        assertThat(card2.getNumber()).isEqualTo(3);
    }

    @Test
    public void testCardNumberIfAce() throws Exception {
        Card card = new Card("", 'A');
        assertThat(card.getNumber()).isEqualTo(1);
    }
}
