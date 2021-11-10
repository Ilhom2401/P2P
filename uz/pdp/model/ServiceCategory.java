package uz.pdp.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceCategory {
    private String name;
    private long categoryId;

    public ServiceCategory(String name, long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public ServiceCategory() {
        this.categoryId = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }
}
