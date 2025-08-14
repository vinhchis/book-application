package vinhchis.repository;

import vinhchis.BookApplication;
import vinhchis.connection.ConnectionPool;
import vinhchis.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorRepositoryImpl implements AuthorRepository {
//    private final ConnectionFactory connectionFactory = BookApplication.getInstance().getConnectionFactory();

    @Override
    public void save(Author author) throws Exception {
        final String query = "INSERT INTO book_market.Author (name) VALUES (?)";

        final ConnectionPool connectionPool = BookApplication.getInstance().getConnectionPool();
        Connection connection = connectionPool.provide();

        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getName());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    author.setId(generatedKeys.getLong(1));
                }
            }
        } finally {
            connectionPool.pushBack(connection);
        }
    }
}
