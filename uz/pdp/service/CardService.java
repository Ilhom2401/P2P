package uz.pdp.service;

import uz.pdp.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardService implements BaseService<Card, List<Card>> {
    public static List<Card> cards = new ArrayList<>();

    public boolean checkCard(long phoneNumber) {
        for (Card card : cards) {
            if (card.getPhoneNumber() == phoneNumber)
                return true;
        }
        return false;
    }

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> getList() {
        return cards;
    }

    public Card getCard(int i, int phoneNumber) {
        int count = 0;
        for (Card card : cards) {
            if (card.getPhoneNumber() == phoneNumber) {
                ++count;
            }
            if (count == i) return card;
        }
        return null;
    }

    public void fullCard(double amount, Card card) {
        int i = 0;
        for (Card card1 : cards) {
            if (card1.getCardId() == card.getCardId()) {
                cards.get(i).setCash(cards.get(i).getCash() + amount);
            }
            i++;
        }
    }

    public Card getCardId(int cardNumber) {
        for (Card card : cards) {
            if (card.getCardNumber() == cardNumber)
                return card;
        }
        return null;
    }

    public void paymentCash(long cardId, double amount) {
        int i = 0;
        for (Card card : cards) {
            if (card.getCardId()==cardId){
                cards.get(i).setCash(cards.get(i).getCash()-amount);
            }
            i++;
        }
    }
}
