package uz.pdp.model;

public class Card extends BaseModel {
    private double cash;
    private long cardId;
    private long userId;
    private int cardNumber;

    public Card() {
        this.cardId = System.currentTimeMillis();
    }

    public long getUserId() {
        return userId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
