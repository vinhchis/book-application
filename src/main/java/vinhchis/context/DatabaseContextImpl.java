package vinhchis.context;

import vinhchis.entity.Author;
import vinhchis.entity.Book;
import vinhchis.entity.Category;
import vinhchis.repository.AuthorRepositoryImpl;
import vinhchis.repository.BookRepositoryImpl;
import vinhchis.repository.CategoryRepositoryImpl;
import vinhchis.repository.Repository;

public class DatabaseContextImpl implements DatabaseContext {

    @Override
    public <E, R extends Repository<E>> R newRepository(Class<E> entityType) {
        if(entityType == Author.class){
            return (R) new AuthorRepositoryImpl();
        }

        if(entityType == Category.class){
            return (R) new CategoryRepositoryImpl();
        }

        if(entityType == Book.class){
            return (R) new BookRepositoryImpl();
        }

        throw new IllegalArgumentException("There is no repository for " + entityType.getName());
    }

}
