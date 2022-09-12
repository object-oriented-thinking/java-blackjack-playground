package nextstep.blackjack.blackjack;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.member.Participants;
import nextstep.blackjack.blackjack.service.BlackjackGameService;
import nextstep.blackjack.blackjack.service.BlackjackOutputService;
import nextstep.blackjack.blackjack.utils.IOService;

public class Main {
    private final static IOService ioService = new IOService();
    private final static BlackjackGameService blackjackGame = new BlackjackGameService(ioService);
    private final static BlackjackOutputService blackjackResult = new BlackjackOutputService(ioService);

    public static void main(String[] args) {
        Dealer dealer = blackjackGame.enterDealer();
        Participants participants = blackjackGame.enter();
        blackjackGame.weatherToAcceptCard(participants);
        blackjackResult.distributeMoney(participants, dealer);
    }
}
