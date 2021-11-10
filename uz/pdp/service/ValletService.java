package uz.pdp.service;

import uz.pdp.History.History;
import uz.pdp.model.Vallet;

import java.util.ArrayList;
import java.util.List;

public class ValletService {
    public static List<Vallet> vallets = new ArrayList<>();

    public static void addVallet(Vallet vallet) {
        vallets.add(vallet);
    }

    public double getCashBack(int phoneNumber) {
        int i = 1;
        double sum = 0;
        for (Vallet vallet : this.vallets) {
            if (vallet.getPhoneNumber() == phoneNumber) {
                System.out.println(vallet.getName()+" : "+vallet.getAmount());
                if (vallet.getName().length()==0)
                sum += vallet.getAmount();
            }
        }
        return sum;
    }
}
