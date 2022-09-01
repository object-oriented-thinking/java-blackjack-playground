package nextstep.blackjack.blackjack.fixture;

import nextstep.blackjack.blackjack.domain.Card;
import nextstep.blackjack.blackjack.domain.CardNumber;
import nextstep.blackjack.blackjack.domain.CardType;
import nextstep.blackjack.blackjack.domain.OneCards;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OneCardFixture {
    public static final Card 첫_번째_카드 = new Card(CardType.CLOVER, CardNumber.ACE);
    public static final Card 두_번째_카드 = new Card(CardType.CLOVER, CardNumber.TWO);
    public static final Card 세_번째_카드 = new Card(CardType.CLOVER, CardNumber.THREE);
    public static final Card 네_번쨰_카드 = new Card(CardType.CLOVER, CardNumber.FOUR);
    private static final Card 다섯_번째_카드 = new Card(CardType.CLOVER, CardNumber.JACK);
    private static final Card 여섯_번째_카드 = new Card(CardType.HEART, CardNumber.JACK);
    public static final List<Card> CARD_LIST = Arrays.asList(첫_번째_카드, 두_번째_카드, 세_번째_카드, 네_번쨰_카드, 다섯_번째_카드, 여섯_번째_카드);

}
