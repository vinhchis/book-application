package vinhchis.factory;

import vinhchis.entities.Author;
import vinhchis.entities.Book;
import vinhchis.entities.Category;

public class EntityFactoryImpl implements  EntityFactory {
    private final AuthorFactory authorFactory = new AuthorFactoryImpl();
    private final CategoryFactory categoryFactory = new CategoryFactoryImpl();
    private final BookFactory bookFactory = new BookFactoryImpl();

    @Override
    public <E> E newEntity(Class<E> classType, String name) {
        if(classType == Author.class) return (E) authorFactory.newAuthor(name);
        if(classType == Category.class) return (E) categoryFactory.newCategory(name);
        if(classType == Book.class) return (E) bookFactory.newBook(name);

        throw new IllegalArgumentException("There is no factory for " + classType.getName());
    }
}
