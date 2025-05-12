package vinhchis;

import vinhchis.builder.AuthorBuilder;
import vinhchis.builder.BookBuilder;
import vinhchis.builder.CategoryBuilder;
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


//        final Author author = entityFactory.newEntity(Author.class, "Chis");
        final Author author = entityFactory
                .newEntityBuilder(AuthorBuilder.class)
                .name("Peter")
                .build();

        authorRepository.save(author);

        final Category category = entityFactory
                .newEntityBuilder(CategoryBuilder.class)
                .name("OOP").build();
        categoryRepository.save(category);

        final Book book = entityFactory
                .newEntityBuilder(BookBuilder.class)
                .name("Java OOP")
                .authorId(author.getId())
                .categoryId(category.getId())
                .build();
        bookRepository.save(book);

    }
}
