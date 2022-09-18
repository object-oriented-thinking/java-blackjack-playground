package blackjack.domain.person;

import blackjack.domain.card.CardFactory;
import blackjack.view.InputView;

import java.util.List;

public class Participants {
    private final CardFactory cardFactory;
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(List<Player> players) {
        this.cardFactory = new CardFactory();
        this.dealer = new Dealer();
        this.players = players;
    }

    public void initCards() throws Exception {
        dealer.initCards(cardFactory);
        for(Player player : players){
            player.initCards(cardFactory);
        }
    }

    public void getMoreCard(InputView inputView) throws Exception {
        //player 카드 추가 할/말
        for(Player player : players){
            while(player.needMoreCard()) {
                if(inputView.wantMoreCard(player)) {
                    player.getMoreCard(cardFactory);
                    System.out.println(player.getAllCards());
                    continue;
                }
                break;
            }
        }

        // dealer 카드 한장 추가 할/말
        if(dealer.needMoreCard()){
            dealer.getMoreCard(cardFactory);
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public void match() {
        // 딜러가 21 초과시 모든 플레이어 승리
        if (dealer.getTotal()>21){
            for(Player player : players) {
                player.win(dealer);
                dealer.changeStatus(Status.LOSE);
            }
        }

        for(Player player : players) {
            player.match(dealer);
        }
    }

    public String getResultCards() {
        String ret = "";
        ret += dealer.getAllCards() + " - 결과: " +dealer.getTotal() + "\n";
        for(Player player : players) {
            ret += player.getAllCards() + " - 결과: " +player.getTotal() + "\n";
        }
        return ret;
    }

    public String getIncomes() {
        String ret = "";
        ret += dealer.getIncome();
        for(Player player : players) {
            ret += player.getIncome();
        }
        return ret;
    }
}
