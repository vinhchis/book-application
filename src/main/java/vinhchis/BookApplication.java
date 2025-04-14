package vinhchis;

import lombok.Getter;
import vinhchis.connection.ConnectionFactory;
import vinhchis.connection.ConnectionFactoryImpl;
import vinhchis.context.DatabaseContext;
import vinhchis.context.DatabaseContextImpl;

public class BookApplication {
    @Getter // generate a getter "getConnectionFactory()"
    private final ConnectionFactory connectionFactory;
    
    @Getter
    private final DatabaseContext databaseContext;

    private static final BookApplication INSTANCE = new BookApplication();
    private BookApplication() {
        connectionFactory = new ConnectionFactoryImpl();
        databaseContext = new DatabaseContextImpl();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }

}
