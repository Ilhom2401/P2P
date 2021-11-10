package uz.pdp.model;

public class Service {
    private String name;
    private long id;
    private long categoryId;

    public Service(String name, long id, long categoryId) {
        this.name = name;
        this.id = id;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public Service() {
        this.id = System.currentTimeMillis();
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

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
