package nextstep.blackjack.blackjack.service;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.member.Participants;
import nextstep.blackjack.blackjack.onecards.OneCards;
import nextstep.blackjack.blackjack.utils.IOService;

public class BlackjackOutputService {
    private final IOService ioService;

    public BlackjackOutputService(IOService ioService) {
        this.ioService = ioService;
    }

    /**
     * 참여자들을 입력 받아 결과를 반환한다.*
     * @param members
     */
    public void distributeMoney(Participants members, Dealer dealer) {

    }
}
