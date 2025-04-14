package vinhchis.repository;

public interface Repository<T> {
    public void save(T entity) throws Exception;
}
