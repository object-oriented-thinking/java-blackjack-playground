package nextstep.blackjack.blackjack.service;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.member.Participant;
import nextstep.blackjack.blackjack.member.Participants;
import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;
import nextstep.blackjack.blackjack.onecards.OneCardsGenerator;
import nextstep.blackjack.blackjack.utils.IOService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackInputService {
    private final static OneCards oneCards = OneCardsGenerator.generateOneCards();
    private final IOService ioService;

    public BlackjackInputService(IOService ioService) {
        this.ioService = ioService;
    }

    /**
     * 참여자들을 입력받고 금액을 입력받는다.*
     *
     * @return Participants
     */
    public Participants enter() {
        Map<String, BigDecimal> participantNameAndBatting = ioService.inputParticipantNameAndBattingMoney();
        return new Participants(
            participantNameAndBatting.entrySet().stream()
                .map(entry -> getParticipant(oneCards, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList())
        );
    }

    /**
     * 참여자들에게 카드를 받을 지 묻는다. 21 이상이면 카드를 받을 수 없다.*
     *
     * @param participants
     */
    public void weatherToAcceptCard(Participants participants) {
        participants.getParticipants()
            .forEach(participant -> {
                while (participant.getCards().isNotOver21() && ioService.weatherToAcceptCard(participant.getUsername(), participant.getCards().sumAll())) {
                    Cards cards = participant.getCards().putCard(oneCards.pollCard());
                    participant.updateCards(cards);
                }
            });
    }

    /**
     * 딜러에게 카드를 준다.*
     *
     * @return Dealer
     */
    public Dealer enterDealer() {
        return new Dealer(oneCards);
    }

    private Participant getParticipant(OneCards oneCards, String username, BigDecimal battingMoney) {
        return new Participant(username, battingMoney, oneCards);
    }


}
