package vinhchis.factory;

import vinhchis.entities.Author;

public class AuthorFactoryImpl implements AuthorFactory{
    @Override
    public Author newAuthor(String name) {
        final Author author = new Author();
        author.setName(name);
        return author;
    }
}
