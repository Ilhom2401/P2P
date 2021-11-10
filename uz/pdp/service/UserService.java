package uz.pdp.service;

import uz.pdp.model.Card;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService implements BaseService<User, List<User>> {
    public static List<User> users = new ArrayList<>();

    public static double commission = 1;
    public static double systemBalance;
    public static double cashBack = 0.3;

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void setCashBack(double cashBack) {
        this.cashBack = cashBack;
    }

    public UserService() {
        User user = new User();
        user.setUserName("superAdmin");
        user.setPassword("root");
        user.setAdmin(true);
        user.setPhoneNumber(777);
        users.add(user);
    }

    public User checkUser(String userName, String password) {
        for (User user1 : users) {
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password))
                return user1;
        }
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    public String getUserName(long cardId) {
        for (Card card : CardService.cards) {
            if (card.getCardId() == cardId) {
                for (User user : users) {
                    if (user.getId() == card.getUserId()) {
                        return user.getUserName();
                    }
                }
            }
        }
        return "";
    }

    public void p2P(Card userCard, long cardId, double amount, int phoneNumber) {
        int i = 0, j = 0;

        for (Card card : CardService.cards) {
            if (card.getCardId() == userCard.getCardId()) {
                double comm = (100 + commission) / 100;
                CardService.cards.get(i).setCash(CardService.cards.get(i).getCash() - amount * comm);
                systemBalance = systemBalance + (amount * (commission - cashBack)) / 100;
            }
            i++;
        }
        for (Card card1 : CardService.cards) {
            if (card1.getCardId() == cardId) {
                CardService.cards.get(j).setCash(CardService.cards.get(j).getCash() + amount);
            }
            j++;
        }
    }

    @Override
    public List<User> getList() {
        return users;
    }
}
