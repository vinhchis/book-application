package vinhchis.factory;

import vinhchis.builder.CategoryBuilder;
import vinhchis.entities.Category;

public interface CategoryFactory {
    Category newCategory(String name);
    CategoryBuilder newCategoryBuilder();
}
