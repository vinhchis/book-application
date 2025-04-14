package vinhchis.factory;

import vinhchis.entities.Book;

public class BookFactoryImpl implements BookFactory{
    @Override
    public Book newBook(String name) {
        final Book book = new Book();
        book.setName(name);
        return book;
    }
}
