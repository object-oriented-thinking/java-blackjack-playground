package nextstep.blackjack.blackjack;

import nextstep.blackjack.blackjack.service.BlackjackGame;
import nextstep.blackjack.blackjack.utils.IOService;

public class Main {
    public static void main(String[] args) {
        IOService ioService = new IOService();
        BlackjackGame blackjackGame = new BlackjackGame(ioService);

        blackjackGame.start();
    }
}
