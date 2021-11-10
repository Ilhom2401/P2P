package uz.pdp.service;

import uz.pdp.History.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    List<History> histories = new ArrayList<>();

    public void addHistory(History history){
        histories.add(history);
    }

    public void getHistory(long id){
        System.out.println("KIRIM <---");
        int i=1,j=1;
        for (int i1=histories.size()-1;i1>=0;i1--) {
           if (histories.get(i1).getType().equals("KIRIM")&&histories.get(i1).getFromUserId()==id)
               System.out.println((i++)+") Kimdan : "+histories.get(i1).getUserNameO()+" | "+histories.get(i1).getSum()+" | "+histories.get(i1).getCurrentTime());

        }
        System.out.println();
        System.out.println("______________________________________");
        System.out.println("CHIQIM --->");
        for (int i1=histories.size()-1;i1>=0;i1--) {
            if (histories.get(i1).getType().equals("CHIQIM")&&histories.get(i1).getFromUserId()==id)
                System.out.println((j++)+") Kimga : "+histories.get(i1).getUserNameI()+" | "+histories.get(i1).getSum()+" | "+histories.get(i1).getCurrentTime());

        }
    }
}
