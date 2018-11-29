package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import eu.busi.projetpizza.model.Category;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * created by  eric.nyandwi on Nov,15/11/2018
 */
public class CategorieConveterTest {
    public static final String  NAME_1= "normal";
    public static final String  NAME_2 = "american";

    @Test(expected = IllegalArgumentException.class)
    public void testCategoryEntityNullToCategory() {
        CategorieConveter.CategoryEntityToCategoryModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCategoryNullToCategoryEntity() {
        CategorieConveter.CategoryModelToCategoryEntity(null);
    }

    @Test
    public void testToCategory() {

        CategoryEntity  categoryEntity = createCategoryEnytiy();
        Category category = CategorieConveter.CategoryEntityToCategoryModel(categoryEntity);
        assertNotNull(category);
        assertEquals(NAME_1, category.getName());

    }

    @Test
    public void testToCategoryEntity() {
        Category category = createCategory();
        CategoryEntity categoryEntity = CategorieConveter.CategoryModelToCategoryEntity(category);
        assertNotNull(categoryEntity);
        validateCategortyEntity(categoryEntity);
    }

    public void validateCategortyEntity(CategoryEntity categoryEntity) {
        assertEquals(NAME_1, categoryEntity.getName());
    }

    /**
     * Création d'un objet Category {@link eu.busi.projetpizza.model.Category}
     * @return
     */
    public static  Category createCategory() {
        Category category = new Category();
        category.setName(NAME_1);
        return category;
    }


    /**
     * Création d'un objet CategoryEntity {@link CategoryEntity}
     * @return
     */
    public static CategoryEntity createCategoryEnytiy() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(NAME_1);
        return categoryEntity;
    }

}