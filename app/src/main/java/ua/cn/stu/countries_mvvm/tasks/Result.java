package ua.cn.stu.countries_mvvm.tasks;

public class Result<T> {

    private final Status status;
    private final T data;

    public enum Status {
        EMPTY,
        LOADING,
        SUCCESS,
        ERROR
    }

    public Result(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
    }

    public static <T> Result<T> error(Throwable error) {
        return new Result<>(Status.ERROR,null, error);
    }

    public static <T> Result<T> empty() {
        return new Result<>(Status.EMPTY,null,null);
    }

    public static <T> Result<T> success(T data) {
        return  new Result<>(Status.SUCCESS,data,null);
    }

    public static<T> Result<T> loading() {
        return new Result<>(Status.LOADING,null,null);
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
