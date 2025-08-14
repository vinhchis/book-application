package vinhchis;

import lombok.Getter;
import vinhchis.connection.ConnectionPool;
import vinhchis.context.DatabaseContext;
import vinhchis.context.DatabaseContextImpl;
import vinhchis.factory.EntityFactory;
import vinhchis.factory.EntityFactoryImpl;

public final class BookApplication {
    @Getter
    private final ConnectionPool connectionPool;
//    @Getter
//    private final ConnectionFactory connectionFactory;
    @Getter
    private final DatabaseContext databaseContext;
    @Getter
    private final EntityFactory entityFactory;

    private static final BookApplication INSTANCE = new BookApplication();

    private BookApplication() {
        databaseContext = new DatabaseContextImpl();
//        connectionFactory = new ConnectionFactoryImpl();
        entityFactory = new EntityFactoryImpl();
        connectionPool = new ConnectionPool();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }

}
