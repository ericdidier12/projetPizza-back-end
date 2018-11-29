package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.IngredientEntity;
import eu.busi.projetpizza.dataAcces.entity.PizzaEntity;
import eu.busi.projetpizza.model.Ingredient;
import eu.busi.projetpizza.model.Pizza;
import java.util.ArrayList;
import java.util.List;

/**
 * created by  eric.nyandwi on Nov,12/11/2018
 */
public class PizzaConveter {

    public static Pizza pizzaEntityTopizzaModel(PizzaEntity pizzaEntity) {
        if (pizzaEntity == null) {
            throw new IllegalArgumentException(" Objet PizzaEntity ne peut pas être null ");
        }
        Pizza  pizza = new Pizza();
        pizza.setId(pizzaEntity.getId());
        pizza.setName(pizzaEntity.getName());
        pizza.setFixed(pizzaEntity.isFixed());
        pizza.setMonth_promo(pizzaEntity.isMonth_promo());
        pizza.setPrice(pizzaEntity.getPrice());
        pizza.setCategory(CategorieConveter.CategoryEntityToCategoryModel(pizzaEntity.getCategoryEntity()));
        List<Ingredient> ingredients = new ArrayList<>();
        if(pizzaEntity.getIngredientEntityList() != null){
            for (IngredientEntity ingredientEntity : pizzaEntity.getIngredientEntityList()) {
                ingredients.add(IngredientConveter.ingredientIngredientTopizzaModel(ingredientEntity));
            }
            pizza.setIngredients(ingredients);
        }
        return pizza;
    }

    public static PizzaEntity pizzaModelTopizzaEntity(Pizza pizza) {
        if (pizza == null) {
            throw new IllegalArgumentException(" Objet Pizza ne peut pas être null ");
        }
        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setId(pizza.getId());
        pizzaEntity.setName(pizza.getName());
        pizzaEntity.setFixed(pizza.isFixed());
        pizzaEntity.setMonth_promo(pizza.isMonth_promo());
        pizzaEntity.setPrice(pizza.getPrice());
        if(pizza.getCategory() != null) {
            pizzaEntity.setCategoryEntity(CategorieConveter.CategoryModelToCategoryEntity(pizza.getCategory()));
        }
        List<IngredientEntity> c  = new ArrayList<>();
        if (pizza.getIngredients() != null) {
            for (Ingredient ingredient : pizza.getIngredients()) {
                c.add(IngredientConveter.ingredientIngredientToIngredientEntity(ingredient));
            }
            pizzaEntity.setIngredientEntityList(c);
        }
        return pizzaEntity;
    }
}
