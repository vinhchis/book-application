package vinhchis.factory;

import vinhchis.builder.AuthorBuilder;
import vinhchis.entity.Author;

public interface AuthorFactory {
    Author newAuthor(String name);
    AuthorBuilder newAuthorBuilder();

}
