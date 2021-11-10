package uz.pdp.model;

public class ServiceField {
    private String name;
    private String title;
    private long serviceId;
    private long id;

    public ServiceField(String name, String title, long serviceId, long id) {
        this.name = name;
        this.title = title;
        this.serviceId = serviceId;
        this.id = id;
    }

    public ServiceField() {
        this.id=System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getId() {
        return id;
    }
}
