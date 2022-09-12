package nextstep.blackjack.blackjack.service;

import nextstep.blackjack.blackjack.member.Participant;
import nextstep.blackjack.blackjack.member.Participants;
import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;
import nextstep.blackjack.blackjack.utils.IOService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackInputService {
    private final IOService ioService;
    private final OneCards oneCards;

    public BlackjackInputService(IOService ioService, OneCards oneCards) {
        this.ioService = ioService;
        this.oneCards = oneCards;
    }

    public void start() {
        Participants participants = enter(oneCards);
        weatherToAcceptCard(participants, oneCards);
    }

    private Participants enter(OneCards oneCards) {
        Map<String, BigDecimal> participantNameAndBatting = ioService.inputParticipantNameAndBattingMoney();
        return new Participants(
            participantNameAndBatting.entrySet().stream()
                .map(entry -> getParticipant(oneCards, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList())
        );
    }

    private void weatherToAcceptCard(Participants participants, OneCards oneCards) {
        participants.getParticipants()
            .forEach(participant -> {
                while (participant.getCards().isNotOver21() && ioService.weatherToAcceptCard(participant.getUsername(), participant.getCards().sumAll())) {
                    Cards cards = participant.getCards().putCard(oneCards.pollCard());
                    participant.updateCards(cards);
                }
            });
    }

    private Participant getParticipant(OneCards oneCards, String username, BigDecimal battingMoney) {
        return new Participant(username, battingMoney, oneCards);
    }

}
