package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.CategoryDAO;
import eu.busi.projetpizza.dataAcces.dao.IngredientDAO;
import eu.busi.projetpizza.dataAcces.dao.PizzaDAO;
import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import eu.busi.projetpizza.model.Category;
import eu.busi.projetpizza.model.Ingredient;
import eu.busi.projetpizza.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin(origins="*")
public class PizzaRestController {

    @Autowired
    private PizzaDAO pizzaDao;

    private final IngredientDAO ingredientDAO;
    private final CategoryDAO categoryDAO;

    public PizzaRestController(IngredientDAO ingredientDAO, CategoryDAO categoryDAO) {
        this.ingredientDAO = ingredientDAO;
        this.categoryDAO = categoryDAO;
    }

    @GetMapping(value="")
    public List<Pizza> getAll(){
        return pizzaDao.listPizza();
    }

    /**
     *  <p>
     *      <br> Give the list any ingredients</br>
     *  </p>
     * @return List of ingredients
     */
    @GetMapping(value = "/ingredients")
    public List<Ingredient> getAllingredient(){
        return ingredientDAO.getAllIngredients();
    }

    /**
     *  <p>
     *      <br> Allows to sort  of list pizza by category </br>
     *  </p>
     * @param name ** name of type String, this is name of Category **
     * @return   ** List of type Pizza **
     *
     *
     */
    @RequestMapping(value = "/trieCategorieByName/{id}", method = RequestMethod.GET)
    public List<Pizza> triCategoryByName(@PathVariable(value = "id") String name) {
        List<Pizza> pizzas = new ArrayList<>();
        if(name != null){
            CategoryEntity categoryEntity = categoryDAO.getCategoriyEntityByName(name);
            if (categoryEntity != null) {
                pizzas = pizzaDao.findByCategoryEntity(categoryEntity);
            }
        }
        return pizzas;
    }


    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<Category> triCategoryByName() {
          return categoryDAO.getListCategories();
    }




}
