package blackjack.domain.person;

public class Player extends Person{
    private final String name;
    private final Integer betAmount;

    public String getName() {
        return name;
    }

    public Player(String name, Integer betAmount) {
        super();
        this.name = name;
        this.betAmount = betAmount;
    }

    @Override
    public boolean needMoreCard() {
        return this.getTotal()<21;
    }

    @Override
    public String getAllCards(){
        return this.name+"카드: "+cards.getAllCards();
    }

    public void match(Dealer dealer){
        // player의 status가 PROCESSING이라면 승부 시작
        if (this.getStatus() == Status.PROCESSING) {
            int thisPlayerScore = this.getTotal();
            int dealerScore = dealer.getTotal();

            // player total > 21이면
            if(thisPlayerScore>21) {
                // player lose, 배팅금액 lose
                this.changeStatus(Status.LOSE);
                this.addIncome(-this.betAmount);
                dealer.addIncome(this.betAmount);
                return;
            }

            // player total == 21이고, card 개수 2개일 때
            if(thisPlayerScore==21 && this.cards.getSize()==2){
                // player win, 배팅금액 * 1.5배 get
                this.changeStatus(Status.LOSE);
                this.addIncome(this.betAmount*1.5);
                dealer.addIncome(-this.betAmount*1.5);
                return;
            }

            // player total ==21 && dealer total ==21
            if(thisPlayerScore ==21 && dealerScore==21){
                // player draw, 배팅금액 get
                this.changeStatus(Status.DRAW);
                this.addIncome(this.betAmount);
                dealer.addIncome(-this.betAmount);
                return;
            }

            // player total > dealer total
            if(thisPlayerScore > dealerScore){
                // player win, 배팅금액 get
                this.changeStatus(Status.WIN);
                this.addIncome(this.betAmount);
                dealer.addIncome(-this.betAmount);
                return;
            }

            // player total < dealer total
            if(thisPlayerScore < dealerScore){
                // player lose, 배팅금액 lose
                this.changeStatus(Status.LOSE);
                this.addIncome(-this.betAmount);
                dealer.addIncome(this.betAmount);
                return;
            }
        }
    }

    @Override
    public String getIncome() {
        return this.name + ": " + this.income +"\n";
    }

    public void win(Dealer dealer) {
        this.changeStatus(Status.WIN);
        this.addIncome(this.betAmount);
        dealer.addIncome(-this.betAmount);
    }
}
