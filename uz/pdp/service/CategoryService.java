package uz.pdp.service;

import uz.pdp.model.ServiceCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryService implements BaseService<ServiceCategory, List<ServiceCategory>> {
    public static List<ServiceCategory> categoryList = new ArrayList<>();


    @Override
    public List<ServiceCategory> getList() {
        return categoryList;
    }

    @Override
    public void add(ServiceCategory category) {
        categoryList.add(category);
    }

    public long getCategoryId(int i) {
        int ind = 0;
        for (ServiceCategory serviceCategory : categoryList) {
            if (ind == i - 1) return serviceCategory.getCategoryId();
            ind++;
        }
        return -1;
    }

    public String getCategoryName(long categoryId) {
        for (ServiceCategory serviceCategory:categoryList) {
            if (serviceCategory.getCategoryId()==categoryId)return serviceCategory.getName();
        }
        return "";
    }
}
