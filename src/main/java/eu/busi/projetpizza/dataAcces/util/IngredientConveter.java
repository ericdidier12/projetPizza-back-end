package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.IngredientEntity;
import eu.busi.projetpizza.model.Ingredient;

/**
 * created by  eric.nyandwi on Nov,12/11/2018
 */
public class IngredientConveter {

    public static Ingredient ingredientIngredientTopizzaModel(IngredientEntity ingredientEntity) {
        if (ingredientEntity == null) {
            throw new IllegalArgumentException(" Objet IngredientEntity ne peut pas être null ");
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientEntity.getId());
        ingredient.setName(ingredientEntity.getName());
        ingredient.setStock_quantity(ingredientEntity.getStock_quantity());
        ingredient.setUnit_price(ingredientEntity.getUnit_price());
        ingredient.setRecipe_qunatity(ingredientEntity.getRecipe_quantity());
        return ingredient;
    }

    public static IngredientEntity ingredientIngredientToIngredientEntity(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException(" Objet ingredient ne peut pas être null ");
        }
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(ingredient.getId());
        ingredientEntity.setName(ingredient.getName());
        ingredientEntity.setRecipe_quantity(ingredient.getRecipe_qunatity());
        ingredientEntity.setStock_quantity(ingredient.getStock_quantity());
        ingredientEntity.setUnit_price(ingredient.getUnit_price());
        return  ingredientEntity ;
    }








}
