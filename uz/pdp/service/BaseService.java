package uz.pdp.service;

public interface BaseService<T, R> {
    public R getList();

    public void add(T t);

}
