package vinhchis.factory;

import vinhchis.builder.AuthorBuilder;
import vinhchis.entities.Author;

public interface AuthorFactory {
    Author newAuthor(String name);
    AuthorBuilder newAuthorBuilder();

}
