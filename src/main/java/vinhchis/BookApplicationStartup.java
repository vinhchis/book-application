package vinhchis;

import vinhchis.context.DatabaseContext;
import vinhchis.entities.Author;
import vinhchis.entities.Book;
import vinhchis.entities.Category;
import vinhchis.repository.AuthorRepository;
import vinhchis.repository.BookRepository;
import vinhchis.repository.CategoryRepository;

public class BookApplicationStartup {
    public static void main(String[] args) throws Exception {
        final BookApplication application = BookApplication.getInstance();
        final DatabaseContext databaseContext = application.getDatabaseContext();
        final AuthorRepository authorRepository = databaseContext.newRepository(Author.class);
        final CategoryRepository categoryRepository = databaseContext.newRepository(Category.class);
        final BookRepository bookRepository = databaseContext.newRepository(Book.class);

        final Author author = new Author();
        author.setName("Dzung");
        authorRepository.save(author);

        final Category category = new Category();
        category.setName("Technology");
        categoryRepository.save(category);

        final Book book = new Book();
        book.setName("Design Patterns");
        book.setAuthorId(author.getId());
        book.setCategoryId(category.getId());
        bookRepository.save(book);

    }
}
