
package eu.busi.projetpizza.dataAcces.dao;

import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import eu.busi.projetpizza.dataAcces.entity.PizzaEntity;
import eu.busi.projetpizza.dataAcces.repository.PizzaRepository;
import eu.busi.projetpizza.dataAcces.util.PizzaConveter;
import eu.busi.projetpizza.model.Pizza;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class PizzaDAO {

    private final PizzaRepository pizzaRepository;

    public PizzaDAO(PizzaRepository pizzaRepository) {

        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> loadAllPizza() {
        List<PizzaEntity> pizzaEntitieslist = pizzaRepository.findAll();
        return pizzaEntitieslist;
    }

    public Pizza savePizza(PizzaEntity pizzaEntity) {
        PizzaEntity pizzaEntitySave = pizzaRepository.save(pizzaEntity);
        return PizzaConveter.pizzaEntityTopizzaModel(pizzaEntitySave);
    }

    public List<Pizza> listPizza() {
        List<PizzaEntity> pizzaEntities = pizzaRepository.findAll();
        List<Pizza> pizzas = new ArrayList<>();

        for (PizzaEntity pizzaEntity : pizzaEntities) {
            pizzas.add(PizzaConveter.pizzaEntityTopizzaModel(pizzaEntity));
        }
        return pizzas;
    }

    public List<Pizza> findByCategoryEntity(CategoryEntity category) {
        List<PizzaEntity> pizzaEntities = pizzaRepository.findByCategoryEntity(category);
        List<Pizza> pizzas = pizzaEntities.stream().
                map(pizzaEntity -> PizzaConveter.pizzaEntityTopizzaModel(pizzaEntity)).collect(Collectors.toList());
        return pizzas;
    }

    public  Pizza findPizzaById(Long id){
        PizzaEntity pizzaEntity= pizzaRepository.findOne(id);
        return PizzaConveter.pizzaEntityTopizzaModel(pizzaEntity);
    }
}
