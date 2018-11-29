

package eu.busi.projetpizza.dataAcces.repository;

import eu.busi.projetpizza.dataAcces.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IngredientRepository extends JpaRepository<IngredientEntity,Long> {}
