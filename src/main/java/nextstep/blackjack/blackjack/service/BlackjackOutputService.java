package nextstep.blackjack.blackjack.service;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;
import nextstep.blackjack.blackjack.utils.IOService;

import java.util.Map;

public class BlackjackOutputService {
    private final IOService ioService;
    private final OneCards oneCards;

    public BlackjackOutputService(IOService ioService, OneCards oneCards) {
        this.ioService = ioService;
        this.oneCards = oneCards;
    }

    public void distributeMoney(Map<String, Cards> members) {
        Dealer dealer = new Dealer(oneCards);

    }
}
