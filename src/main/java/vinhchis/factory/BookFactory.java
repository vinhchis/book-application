package vinhchis.factory;

import vinhchis.entities.Book;

public interface BookFactory {
    Book newBook(String name);
}
