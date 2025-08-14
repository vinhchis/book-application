package vinhchis.factory;

import vinhchis.builder.AuthorBuilder;
import vinhchis.entity.Author;

public class AuthorFactoryImpl implements AuthorFactory{
    @Override
    public Author newAuthor(String name) {
        final Author author = new Author();
        author.setName(name);
        return author;
    }

    @Override
    public AuthorBuilder newAuthorBuilder() {
        return new AuthorBuilder();
    }
}
