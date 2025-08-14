package vinhchis.factory;

import vinhchis.builder.CategoryBuilder;
import vinhchis.entity.Category;

public interface CategoryFactory {
    Category newCategory(String name);
    CategoryBuilder newCategoryBuilder();
}
