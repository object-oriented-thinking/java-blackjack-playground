package blackjack.domain.person;

import blackjack.domain.card.CardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    Dealer dealer;
    CardFactory cardFactory;

    @BeforeEach
    void setUp(){
        dealer = new Dealer();
        cardFactory = new CardFactory();
    }

    @Test
    void initTest() throws Exception {
        dealer.initCards(cardFactory);
        assertThat(dealer.cards.getSize()).isEqualTo(2);
    }

    @Test
    void getMoreTest() throws Exception {
        dealer.getMoreCard(cardFactory);
        assertThat(dealer.cards.getSize()).isEqualTo(1);
    }

}
