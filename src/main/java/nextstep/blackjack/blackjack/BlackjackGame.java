package nextstep.blackjack.blackjack;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.member.Participant;
import nextstep.blackjack.blackjack.member.Participants;
import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;
import nextstep.blackjack.blackjack.utils.IOService;
import nextstep.blackjack.blackjack.onecards.OneCardsGenerator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final IOService ioService;

    public BlackjackGame(IOService ioService) {
        this.ioService = ioService;
    }

    public void start() {
        OneCards oneCards = OneCardsGenerator.generateOneCards();

        Participants participants = enter(oneCards);

        Dealer dealer = new Dealer(oneCards);

        participants.distribute(oneCards);

        weatherToAcceptCard(participants, oneCards);

        getResult(dealer, participants);
    }

    private Participants enter(OneCards oneCards) {
        Map<String, BigDecimal> participantNameAndBatting = ioService.inputParticipantNameAndBattingMoney();
        return new Participants(
            participantNameAndBatting.entrySet().stream()
                .map(entry -> getParticipant(oneCards, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList())
        );
    }

    private void getResult(Dealer dealer, Participants participants) {
    }

    private void weatherToAcceptCard(Participants participants, OneCards oneCards) {
        participants.getParticipants().stream()
            .filter(participant -> participant.getCards().sumAll() < 21)
            .forEach(participant -> {
                while (ioService.weatherToAcceptCard(participant.getUsername())) {
                    participant.getCards().putCard(oneCards.pollCard());
                }
            });
    }

    private Participant getParticipant(OneCards oneCards, String username, BigDecimal battingMoney) {
        return new Participant(username, battingMoney, new Cards(oneCards.pollCard(), oneCards.pollCard()));
    }

}
