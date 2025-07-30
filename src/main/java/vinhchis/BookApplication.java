package vinhchis;

import lombok.Getter;
import vinhchis.connection.ConnectionFactory;
import vinhchis.connection.ConnectionFactoryImpl;
import vinhchis.connection.ConnectionPool;
import vinhchis.context.DatabaseContext;
import vinhchis.context.DatabaseContextImpl;
import vinhchis.factory.EntityFactory;
import vinhchis.factory.EntityFactoryImpl;

public class BookApplication {
//    @Getter // generate a getter "getConnectionFactory()"
//    private final ConnectionFactory connectionFactory;

    @Getter
    private final ConnectionPool connectionPool;
    
    @Getter
    private final DatabaseContext databaseContext;

    @Getter
    private final EntityFactory entityFactory;

    private static final BookApplication INSTANCE = new BookApplication();
    private BookApplication() {
//        connectionFactory = new ConnectionFactoryImpl();
        databaseContext = new DatabaseContextImpl();
        entityFactory = new EntityFactoryImpl();
        connectionPool = new ConnectionPool();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }

}
