package uz.pdp.model;

import java.util.List;

public class User extends BaseModel{
    private String userName;
    private String password;
    private boolean isAdmin;
    private boolean isActive;

    public User(String userName, String password, boolean isAdmin, boolean isActive) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public User() {
        super.id=System.currentTimeMillis();
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
