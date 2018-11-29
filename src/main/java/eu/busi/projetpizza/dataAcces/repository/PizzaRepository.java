
package eu.busi.projetpizza.dataAcces.repository;
import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import eu.busi.projetpizza.dataAcces.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PizzaRepository extends JpaRepository<PizzaEntity,Long> {
    List<PizzaEntity> findByCategoryEntity(CategoryEntity categoryEntity);
}

