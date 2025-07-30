package vinhchis.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vinhchis.BookApplication;
import vinhchis.connection.ConnectionFactory;
import vinhchis.connection.ConnectionPool;
import vinhchis.entities.Book;

public class BookRepositoryImpl implements BookRepository {
//private final ConnectionFactory connectionFactory = BookApplication.getInstance().getConnectionFactory();

    @Override
    public void save(Book book) throws Exception {
        final String query = "INSERT INTO book_market.Book (name, authorId, categoryId) VALUES (?, ?, ?)";
        ConnectionPool connectionPool = BookApplication.getInstance().getConnectionPool();
        Connection connection = connectionPool.provide();

        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getName());
            statement.setLong(2, book.getAuthorId());
            statement.setLong(3, book.getCategoryId());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getLong(1));
                }
            }
        } finally {
            connectionPool.pushBack(connection);
        }

    }

}
