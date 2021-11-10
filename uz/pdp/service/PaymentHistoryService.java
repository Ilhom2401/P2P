package uz.pdp.service;

import uz.pdp.History.PaymentHistory;

import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryService implements BaseService<PaymentHistory, List<PaymentHistory>> {
    public static List<PaymentHistory> paymentHistories = new ArrayList<>();


    @Override
    public List<PaymentHistory> getList() {
        return paymentHistories;
    }

    @Override
    public void add(PaymentHistory paymentHistory) {
        paymentHistories.add(paymentHistory);
    }

    public void getHistory(long userId) {
        for (PaymentHistory paymentHistory : paymentHistories) {
            if (paymentHistory.getUserId() == userId) {
                System.out.println("Karta nomeri : "+paymentHistory.getCardNumber());
                System.out.println("Service : "+paymentHistory.getServiceName());
                System.out.println("Summa : "+paymentHistory.getValue());
                System.out.println("Time : "+paymentHistory.getCurrentTime());
                System.out.println("-------------------------------------------------------");
            }
        }
    }

}
