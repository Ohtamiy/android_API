package ua.cn.stu.countries_mvvm.model;

public interface Callback<T> {
    void onError(Throwable error);
    void onResult(T data);
}
