package nextstep.blackjack.blackjack;

import nextstep.blackjack.blackjack.onecards.OneCards;
import nextstep.blackjack.blackjack.onecards.OneCardsGenerator;
import nextstep.blackjack.blackjack.service.BlackjackInputService;
import nextstep.blackjack.blackjack.service.BlackjackOutputService;
import nextstep.blackjack.blackjack.utils.IOService;

public class Main {
    public static void main(String[] args) {
        OneCards oneCards = OneCardsGenerator.generateOneCards();
        IOService ioService = new IOService();
        BlackjackInputService blackjackGame = new BlackjackInputService(ioService, oneCards);
        BlackjackOutputService blackjackResult = new BlackjackOutputService(ioService, oneCards);
        blackjackGame.start();
//        blackjackResult.distributeMoney(members);
    }
}
