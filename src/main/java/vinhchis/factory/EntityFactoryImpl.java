package vinhchis.factory;

import vinhchis.builder.AuthorBuilder;
import vinhchis.builder.BookBuilder;
import vinhchis.builder.Builder;
import vinhchis.builder.CategoryBuilder;
import vinhchis.entities.Author;
import vinhchis.entities.Book;
import vinhchis.entities.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityFactoryImpl implements  EntityFactory {
    private final AuthorFactory authorFactory = new AuthorFactoryImpl();
    private final CategoryFactory categoryFactory = new CategoryFactoryImpl();
    private final BookFactory bookFactory = new BookFactoryImpl();

    private final Map<Class<?>, Supplier<Builder>> entityBuilderSuppliers;

    public EntityFactoryImpl(){
        entityBuilderSuppliers = new HashMap<>();
        entityBuilderSuppliers.put(AuthorBuilder.class, AuthorBuilder::new);
        entityBuilderSuppliers.put(CategoryBuilder.class, CategoryBuilder::new);
        entityBuilderSuppliers.put(BookBuilder.class, BookBuilder::new);

    }

    @Override
    public <E> E newEntity(Class<E> classType, String name) {
        if(classType == Author.class) return (E) authorFactory.newAuthor(name);
        if(classType == Category.class) return (E) categoryFactory.newCategory(name);
        if(classType == Book.class) return (E) bookFactory.newBook(name);

        throw new IllegalArgumentException("There is no factory for " + classType.getName());

    }

    @Override
    public <E, B extends Builder<E>> B newEntityBuilder(Class<B> builderType) {
        return (B) entityBuilderSuppliers.get(builderType).get();
    }
}

/*
* Supplier<T> - function interface
* get() - dùng phương thức không tham số, trả về một đối tượng
* */
