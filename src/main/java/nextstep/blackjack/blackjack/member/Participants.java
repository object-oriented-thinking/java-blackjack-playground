package nextstep.blackjack.blackjack.member;

import nextstep.blackjack.blackjack.onecards.OneCards;

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
}
