package vinhchis.handler;

public interface VoidHandler<T> extends Handler<T, T> {
    @Override
    default T handle(T input) throws Exception {
        return voidHandle(input);
    }

    T voidHandle(T input) throws Exception;
}
