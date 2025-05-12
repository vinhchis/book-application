package vinhchis;

import vinhchis.context.DatabaseContext;
import vinhchis.entities.Author;
import vinhchis.entities.Book;
import vinhchis.entities.Category;
import vinhchis.factory.EntityFactory;
import vinhchis.repository.AuthorRepository;
import vinhchis.repository.BookRepository;
import vinhchis.repository.CategoryRepository;

public class BookApplicationStartup {
    public static void main(String[] args) throws Exception {
        final BookApplication application = BookApplication.getInstance();
        final DatabaseContext databaseContext = application.getDatabaseContext();
        final EntityFactory entityFactory = application.getEntityFactory();

        final AuthorRepository authorRepository = databaseContext.newRepository(Author.class);
        final CategoryRepository categoryRepository = databaseContext.newRepository(Category.class);
        final BookRepository bookRepository = databaseContext.newRepository(Book.class);


        final Author author = entityFactory.newEntity(Author.class, "Chis");
        authorRepository.save(author);

        final Category category = entityFactory.newEntity(Category.class, "Programming");
        categoryRepository.save(category);

        final Book book = entityFactory.newEntity(Book.class, "Java");
        book.setAuthorId(author.getId());
        book.setCategoryId(category.getId());
        bookRepository.save(book);

    }
}
