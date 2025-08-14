package vinhchis.handler;

public interface Handler<T, R> {
    R handle(T input) throws Exception;
}
