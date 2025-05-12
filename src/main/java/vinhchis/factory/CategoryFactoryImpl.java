package vinhchis.factory;

import vinhchis.builder.CategoryBuilder;
import vinhchis.entities.Category;

public class CategoryFactoryImpl implements CategoryFactory{


    @Override
    public Category newCategory(String name) {
        final Category category = new Category();
        category.setName(name);
        return category;
    }

    @Override
    public CategoryBuilder newCategoryBuilder() {
        return new CategoryBuilder();
    }
}
