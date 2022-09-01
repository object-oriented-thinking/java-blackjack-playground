package nextstep.blackjack.blackjack.service;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.member.Participant;
import nextstep.blackjack.blackjack.member.Participants;
import nextstep.blackjack.blackjack.onecards.Cards;
import nextstep.blackjack.blackjack.onecards.OneCards;
import nextstep.blackjack.blackjack.onecards.OneCardsGenerator;
import nextstep.blackjack.blackjack.utils.IOService;

import java.math.BigDecimal;
import java.util.HashMap;
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
        Map<String, Integer> map = new HashMap<>();
        participants.getParticipants().forEach(participant ->
            map.put(participant.getUsername(), participant.getCards().sumAll())
        );

        map.put("dealer", dealer.getCards().sumAll());

        BigDecimal money = participants.sumBattingMoney();

        ioService.outputResult(map, money);
    }

    private void weatherToAcceptCard(Participants participants, OneCards oneCards) {
        participants.getParticipants().stream()
            .filter(participant -> participant.getCards().isOver21())
            .forEach(participant -> {
                while (participant.getCards().isOver21() && ioService.weatherToAcceptCard(participant.getUsername(), participant.getCards().sumAll())) {
                    Cards cards = participant.getCards().putCard(oneCards.pollCard());
                    participant.updateCards(cards);
                }
            });
    }

    private Participant getParticipant(OneCards oneCards, String username, BigDecimal battingMoney) {
        return new Participant(username, battingMoney, oneCards);
    }

}
