package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import eu.busi.projetpizza.model.Category;

/**
 * created by  eric.nyandwi on Nov,12/11/2018
 */
public class CategorieConveter {

    /**
     * Transforme une entité JPA {@link CategoryEntity} en objet Model {@link Category}.
     *
     * @param categoryEntity
     * @return Objet type Category
     */
    public static Category CategoryEntityToCategoryModel(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            throw new IllegalArgumentException(" objet categoryEntity  ne peut pas être null ");
        }

        Category category = new Category();
        if(categoryEntity.getId() != null) {
            category.setId(categoryEntity.getId());
        }
        category.setName(categoryEntity.getName());
        return category;
    }

/**
 * Transforme une entité JPA {@link CategoryEntity} en objet Model {@link Category}.
 * @param category
 * @return
 */
    public static CategoryEntity CategoryModelToCategoryEntity(Category category) {
        if (category == null) {
            throw new IllegalArgumentException(" objet Category  ne peut pas être null ");
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId((category.getId()));
        categoryEntity.setName(category.getName());
        return categoryEntity ;
    }



}
