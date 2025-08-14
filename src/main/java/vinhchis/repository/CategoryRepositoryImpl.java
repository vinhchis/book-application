package vinhchis.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vinhchis.BookApplication;
import vinhchis.connection.ConnectionPool;
import vinhchis.entity.Category;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public void save(Category category) throws Exception {
        final String query = "INSERT INTO book_market.Category (name) VALUES (?)";

        ConnectionPool connectionPool = BookApplication.getInstance().getConnectionPool();
        Connection connection = connectionPool.provide();

        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setId(generatedKeys.getLong(1));
                }
            }
        } finally {
            connectionPool.pushBack(connection);
        }
    }

}
