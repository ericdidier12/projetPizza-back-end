package eu.busi.projetpizza.dataAcces.dao;

import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import eu.busi.projetpizza.dataAcces.repository.CategoryRepository;
import eu.busi.projetpizza.dataAcces.util.CategorieConveter;
import eu.busi.projetpizza.model.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * created by  eric.nyandwi on Nov,12/11/2018
 */

@Service
@Transactional
public class CategoryDAO {

    private final CategoryRepository categoryRepository;

    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getListCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categories.add(CategorieConveter.CategoryEntityToCategoryModel(categoryEntity));
        }
        return categories;
    }

    public CategoryEntity getCategoriyEntityByName(String name){
        return  categoryRepository.findByName(name);
    }

    public Category getCategoriyByName(String name){
        CategoryEntity categoryEntity = categoryRepository.findByName(name);
        return CategorieConveter.CategoryEntityToCategoryModel(categoryEntity);
    }

}
