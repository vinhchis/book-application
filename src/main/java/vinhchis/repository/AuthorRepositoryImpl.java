package vinhchis.repository;

import vinhchis.BookApplication;
import vinhchis.connection.ConnectionFactory;
import vinhchis.entities.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorRepositoryImpl implements AuthorRepository {
    private final ConnectionFactory connectionFactory = BookApplication.getInstance().getConnectionFactory();

    @Override
    public void save(Author author) throws Exception {
        final String query = "INSERT INTO book_market.Author (name) VALUES (?)";
        try(Connection connection = connectionFactory.newConnection()){
            try(PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
                statement.setString(1, author.getName());
                statement.execute();
                try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        author.setId(generatedKeys.getLong(1));
                    }
                }
            }
        }
    }
}
