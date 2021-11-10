package uz.pdp.service;

import uz.pdp.model.Service;
import uz.pdp.model.ServiceCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceService implements BaseService<Service, List<Service>> {
    public static List<Service> serviceList = new ArrayList<>();


    @Override
    public List<Service> getList() {
        return serviceList;
    }

    @Override
    public void add(Service service) {
        serviceList.add(service);
    }

    public long getServiceId(int i, long categoryId) {
        int ind = 0;

        for (Service service : serviceList) {
            if (service.getCategoryId() == categoryId) {
                if (ind == (i - 1)) {
                    return service.getId();
                }
                ind++;
            }
        }
        return -1;
    }
}
