package vinhchis.factory;

import vinhchis.entities.Author;

public interface AuthorFactory {
    Author newAuthor(String name);
}
