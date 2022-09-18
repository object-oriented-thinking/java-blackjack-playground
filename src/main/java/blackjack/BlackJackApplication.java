package blackjack;

import blackjack.domain.person.Dealer;
import blackjack.domain.person.Participants;
import blackjack.domain.person.Player;
import blackjack.view.InputView;

import java.util.List;

public class BlackJackApplication {
    private static final InputView inputView = new InputView();

    public static void main(String[] args) throws Exception {
        // player name과 배팅 금액 설정 후 객체 생성
        List<Player> players = inputView.makePlayers();
        Participants participants = new Participants(players);

        // 모두에게 카드 2장 할당
        participants.initCards();

        // player에게 카드 추가로 뽑을지 여부 선택하도록 함
        participants.getMoreCard(inputView);

        // 모두의 카드 패, 총점 출력
        System.out.println(participants.getResultCards());

        // 승패 여부 가린 후 최종 수익 계산
        participants.match();

        // 최종 수익 출력
        System.out.println(participants.getIncomes());
    }
}
