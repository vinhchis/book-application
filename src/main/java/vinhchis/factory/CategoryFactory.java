package vinhchis.factory;

import vinhchis.entities.Category;

public interface CategoryFactory {
    Category newCategory(String name);
}
