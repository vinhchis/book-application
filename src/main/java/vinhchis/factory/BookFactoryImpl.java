package vinhchis.factory;

import vinhchis.builder.BookBuilder;
import vinhchis.entity.Book;

public class BookFactoryImpl implements BookFactory{
    @Override
    public Book newBook(String name) {
        final Book book = new Book();
        book.setName(name);
        return book;
    }

    @Override
    public BookBuilder newBookBuilder() {
        return new BookBuilder();
    }
}
