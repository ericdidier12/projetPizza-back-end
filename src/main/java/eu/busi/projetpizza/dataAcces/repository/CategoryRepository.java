package eu.busi.projetpizza.dataAcces.repository;

import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by  eric.nyandwi on Nov,12/11/2018
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String namCategory);
}
