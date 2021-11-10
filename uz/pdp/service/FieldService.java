package uz.pdp.service;

import uz.pdp.model.ServiceField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldService implements BaseService<ServiceField, List<ServiceField>>{
    public static List<ServiceField> fieldList = new ArrayList<>();


    @Override
    public List<ServiceField> getList() {
        return fieldList;
    }

    @Override
    public void add(ServiceField field) {
        fieldList.add(field);
    }
}
