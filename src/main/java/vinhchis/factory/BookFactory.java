package vinhchis.factory;

import vinhchis.builder.BookBuilder;
import vinhchis.entities.Book;

public interface BookFactory {
    Book newBook(String name);
    BookBuilder newBookBuilder();
}
