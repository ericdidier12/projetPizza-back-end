package eu.busi.projetpizza.controllerrest;

import eu.busi.projetpizza.dataAcces.dao.CategoryDAO;
import eu.busi.projetpizza.dataAcces.dao.IngredientDAO;
import eu.busi.projetpizza.dataAcces.dao.PizzaDAO;
import eu.busi.projetpizza.dataAcces.entity.CategoryEntity;
import eu.busi.projetpizza.dataAcces.util.IngredientConveter;
import eu.busi.projetpizza.dataAcces.util.PizzaConveter;
import eu.busi.projetpizza.dataAcces.util.generator.NameGenerator;
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

    private final CategoryDAO categorieDAO;
    private final IngredientDAO ingredientDAO;
    private final CategoryDAO categoryDAO;
    private final PizzaDAO pizzaDAO;
    private static float PRICE_OF_INGREDIENTS = 3;

    public PizzaRestController(CategoryDAO categorieDAO, IngredientDAO ingredientDAO, CategoryDAO categoryDAO, PizzaDAO pizzaDAO) {
        this.categorieDAO = categorieDAO;
        this.ingredientDAO = ingredientDAO;
        this.categoryDAO = categoryDAO;
        this.pizzaDAO = pizzaDAO;
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
    public List<Ingredient> getAllIngredients(){
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

    @RequestMapping(value = "/buildPizzaCustom", method = RequestMethod.POST)
    public  Pizza buildPizzaCustom(@RequestBody List<Integer>  listIdIngredient){
        Pizza pizza = new Pizza();
        Pizza pizzaCustom  = null;
        List<Ingredient> ingredientList = new ArrayList<>();
        for (Integer item : listIdIngredient) {
            Ingredient ingredient = ingredientDAO.loadIngredientById(item);
            if (ingredientDAO.checkIfStockQuantiteAndgetStock_Quantity_IngredientIsNull(IngredientConveter.ingredientIngredientToIngredientEntity(ingredient))) {
                PRICE_OF_INGREDIENTS += ingredient.getUnit_price();
                ingredientList.add(ingredient);
            }
        }
        if (!ingredientList.isEmpty()) {
            pizzaCustom = getPizza(ingredientList, pizza);
        }
        return pizzaCustom;
    }


    private Pizza getPizza (List < Ingredient > ingredientList, Pizza pizza){
        pizza.setFixed(false);
        pizza.setName("PizzaCustom_"+NameGenerator.generateName());
        pizza.setIngredients(ingredientList);
        pizza.setPrice(PRICE_OF_INGREDIENTS);
        pizza.setNumber(1);
        pizza.setCategory(categorieDAO.getCategoriyByName("normal"));
        return pizzaDAO.savePizza(PizzaConveter.pizzaModelTopizzaEntity(pizza));
    }




}
