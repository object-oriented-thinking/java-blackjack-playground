package nextstep.blackjack.blackjack.member;

import nextstep.blackjack.blackjack.onecards.OneCards;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public void distribute(OneCards oneCards) {
        participants.forEach(participant -> participant.getCards().putCard(oneCards.pollCard()));
    }

    public BigDecimal sumBattingMoney() {
        return getParticipants().stream()
            .map(Participant::getBettingMoney)
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }
}
