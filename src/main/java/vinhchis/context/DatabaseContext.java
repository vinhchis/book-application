package vinhchis.context;

import vinhchis.repository.Repository;

public interface DatabaseContext {
    <E, R extends Repository<E>> R newRepository(Class<E> entityType);
}
