package vinhchis.builder;

import vinhchis.entities.Author;
import vinhchis.entities.Category;

public class CategoryBuilder implements Builder<Category> {
    private long id;
    private String name;

    public CategoryBuilder id(long id){
        this.id = id;
        return this;
    }

    public CategoryBuilder name(String name){
        this.name = name;
        return this;
    }

    @Override
    public Category build(){
        final Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }
}
