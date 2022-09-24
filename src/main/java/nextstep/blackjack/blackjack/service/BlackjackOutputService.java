package nextstep.blackjack.blackjack.service;

import nextstep.blackjack.blackjack.member.Dealer;
import nextstep.blackjack.blackjack.member.Participant;
import nextstep.blackjack.blackjack.member.Participants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackOutputService {

    public static final String DEALER = "dealer";

    /**
     * 참여자들을 입력 받아 결과를 반환한다.*
     *
     * @param participants
     */
    public Map<String, BigDecimal> distributeMoney(Participants participants, Dealer dealer) {
        Map<String, BigDecimal> members = new HashMap<>();

        List<Participant> participants_cards_21 = participants.getParticipants().stream()
            .filter(participant -> participant.getCards().is21())
            .collect(Collectors.toList());

        int maxParticipant = participants.getParticipants().stream()
            .filter(participant -> participant.getCards().sumAll() <= 21)
            .max(Comparator.comparingLong(value -> value.getCards().sumAll()))
            .map(participant -> participant.getCards().sumAll())
            .orElse(0);

        if (dealer.getCards().isOver21()) {
            participants.getParticipants()
                .forEach(participant -> members.put(participant.getUsername(), participant.getBettingMoney()));

            BigDecimal dealerProfit = participants.getParticipants().stream()
                .map(Participant::getBettingMoney)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .negate();

            members.put(DEALER, dealerProfit);
            return members;
        }

        if (participants_cards_21.stream().anyMatch(participant -> participant.getCards().isCountTwo())) {
            List<Participant> winners = participants_cards_21.stream()
                .filter(participant -> participant.getCards().isCountTwo())
                .collect(Collectors.toList());

            List<Participant> looser = participants.getParticipants().stream()
                .filter(participant -> !winners.contains(participant))
                .collect(Collectors.toList());

            winners.forEach(participant -> members.put(participant.getUsername(), participant.getBettingMoney().multiply(BigDecimal.valueOf(1.5)).setScale(0, RoundingMode.FLOOR)));
            looser.forEach(participant -> members.put(participant.getUsername(), participant.getBettingMoney().negate()));

            BigDecimal dealerProfit = members.values().stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO).negate();
            members.put(DEALER, dealerProfit);
            return members;
        }

        if (dealer.getCards().is21() && !participants_cards_21.isEmpty()) {
            participants_cards_21.forEach(participant ->
                members.put(participant.getUsername(), participant.getBettingMoney())
            );

            List<Participant> participants_cards_not_21 = participants.getParticipants().stream()
                .filter(participant -> !participant.getCards().is21())
                .collect(Collectors.toList());

            participants_cards_not_21.forEach(participant ->
                members.put(participant.getUsername(), participant.getBettingMoney().negate())
            );

            BigDecimal dealerProfit = members.values().stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO).negate();
            members.put(DEALER, dealerProfit);

            return members;
        }

        if ((dealer.getCards().is21() && participants_cards_21.isEmpty()) || (dealer.getCards().sumAll() > maxParticipant)) {
            participants.getParticipants()
                .forEach(participant -> members.put(participant.getUsername(), participant.getBettingMoney().negate()));

            BigDecimal dealerProfit = members.values().stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO).negate();
            members.put(DEALER, dealerProfit);
            return members;
        }


        if (dealer.getCards().sumAll() <= maxParticipant) {
            List<Participant> winner = participants.getParticipants().stream()
                .filter(participant -> participant.getCards().sumAll() == maxParticipant)
                .collect(Collectors.toList());

            List<Participant> looser = participants.getParticipants().stream()
                .filter(participant -> participant.getCards().sumAll() != maxParticipant)
                .collect(Collectors.toList());

            winner.forEach(participant -> members.put(participant.getUsername(), participant.getBettingMoney()));
            looser.forEach(participant -> members.put(participant.getUsername(), participant.getBettingMoney().negate()));

            BigDecimal dealerProfit = members.values().stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO).negate();
            members.put(DEALER, dealerProfit);


            return members;
        }
        return new HashMap<>();
    }

    public Map<String, Integer> getResult(Participants participants, Dealer dealer) {
        Map<String, Integer> scores = new HashMap<>();
        participants.getParticipants().forEach(participant -> scores.put(participant.getUsername(), participant.getCards().sumAll()));
        scores.put(DEALER, dealer.getCards().sumAll());

        return scores;
    }
}
