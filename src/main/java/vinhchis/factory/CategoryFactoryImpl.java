package vinhchis.factory;

import vinhchis.entities.Category;

public class CategoryFactoryImpl implements CategoryFactory{


    @Override
    public Category newCategory(String name) {
        final Category category = new Category();
        category.setName(name);
        return category;
    }
}
