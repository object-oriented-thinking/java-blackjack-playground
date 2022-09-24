package nextstep.blackjack.blackjack.fixture;

import nextstep.blackjack.blackjack.onecards.Card;
import nextstep.blackjack.blackjack.onecards.CardNumber;
import nextstep.blackjack.blackjack.onecards.CardType;

import java.util.Arrays;
import java.util.List;

public class OneCardFixture {
    public static final Card 첫_번째_카드 = new Card(CardType.CLOVER, CardNumber.ACE);
    public static final Card 두_번째_카드 = new Card(CardType.CLOVER, CardNumber.TWO);
    public static final Card 세_번째_카드 = new Card(CardType.CLOVER, CardNumber.THREE);
    public static final Card 네_번쨰_카드 = new Card(CardType.CLOVER, CardNumber.FOUR);
    private static final Card 다섯_번째_카드 = new Card(CardType.CLOVER, CardNumber.JACK);
    private static final Card 여섯_번째_카드 = new Card(CardType.HEART, CardNumber.JACK);
    private static final Card 일곱_번째_카드 = new Card(CardType.HEART, CardNumber.ACE);
    private static final Card 여덟_번째_카드 = new Card(CardType.DIAMOND, CardNumber.ACE);
    private static final Card 아홉_번째_카드 = new Card(CardType.SPADE, CardNumber.ACE);
    public static final List<Card> CARD_LIST = Arrays.asList(첫_번째_카드, 두_번째_카드, 세_번째_카드, 네_번쨰_카드, 다섯_번째_카드,
        여섯_번째_카드, 일곱_번째_카드, 여덟_번째_카드, 아홉_번째_카드);

}
