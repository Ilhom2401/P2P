package uz.pdp.History;

import java.text.SimpleDateFormat;
import java.util.Date;

public class History {
    private long userId;
    private long toUserId;
    private String type;
    private String currentTime;
    private double sum;
    private String userNameI;
    private String userNameO;

    public String getUserNameI() {
        return userNameI;
    }

    public String getUserNameO() {
        return userNameO;
    }

    public History() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.currentTime = formatter.format(date);
    }

    public History(long userId, long toUserId, String type, double sum, String userNameI, String userNameO) {
        this.userId = userId;
        this.toUserId = toUserId;
        this.type = type;
        this.userNameI = userNameI;
        this.userNameO = userNameO;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.currentTime = formatter.format(date);
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getFromUserId() {
        return userId;
    }

    public void setFromUserId(long fromUserId) {
        this.userId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
