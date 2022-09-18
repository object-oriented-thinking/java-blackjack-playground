package blackjack.domain.person;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;

public abstract class Person {
    protected static final int MAX_NUM = 21;
    protected final Cards cards;
    protected double income;
    private Status status;

    public Person() {
        this.cards = new Cards();
        this.income = 0;
        this.status = Status.PROCESSING;
    }

    protected int getTotal() {
        return cards.getTotal();
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
    public void initCards(CardFactory cardFactory) throws Exception {
        getMoreCard(cardFactory);
        getMoreCard(cardFactory);
    }

    public void getMoreCard(CardFactory cardFactory) throws Exception {
        cards.getMoreCard(cardFactory);
    }

    public abstract boolean needMoreCard();

    public abstract String getAllCards();

    public abstract String getIncome();

    public void addIncome(double income){
        this.income += income;
    }
}
