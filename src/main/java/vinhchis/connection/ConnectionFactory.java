package vinhchis.connection;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection newConnection() throws Exception;
}
