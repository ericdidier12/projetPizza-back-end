package eu.busi.projetpizza.dataAcces.dao;

import eu.busi.projetpizza.dataAcces.entity.IngredientEntity;
import eu.busi.projetpizza.dataAcces.util.IngredientConveter;
import eu.busi.projetpizza.dataAcces.repository.IngredientRepository;


import eu.busi.projetpizza.model.Ingredient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class IngredientDAO {

    private final IngredientRepository ingredientRepository;


    public IngredientDAO(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientEntity saveUpdateIngredient(Ingredient ingredient) {
        IngredientEntity ingredientEntityFromModel = IngredientConveter.ingredientIngredientToIngredientEntity(ingredient);
        IngredientEntity ingredientEntityToDB = ingredientRepository.findOne(ingredient.getId());
        ingredientEntityToDB.setStock_quantity(ingredientEntityFromModel.getStock_quantity());
        IngredientEntity ingredientEntity = ingredientRepository.save(ingredientEntityToDB);
        return  ingredientEntity;

    }

    public Ingredient loadIngredientById(long Id)
    {

        IngredientEntity ingredientEntity = ingredientRepository.findOne(Id);
        Ingredient ingredient = IngredientConveter.ingredientIngredientTopizzaModel(ingredientEntity);
        return ingredient;
    }

    public List<Ingredient> getAllIngredients() {
        List<IngredientEntity> ingredientEntities = ingredientRepository.findAll();
        List<Ingredient> ingredients = ingredientEntities.stream()
                .map(ingredientEntity ->
                        IngredientConveter.ingredientIngredientTopizzaModel(ingredientEntity))
                .collect(Collectors.toList());
        return ingredients;
    }

    public Boolean checkIfStockQuantiteAndgetStock_Quantity_IngredientIsNull(IngredientEntity ingredientEntity) {
        IngredientEntity ingredientEntity1 = ingredientRepository.findOne(ingredientEntity.getId());
        if ( ingredientEntity1.getRecipe_quantity() <= ingredientEntity1.getStock_quantity()) {return true;}
        return false;
    }
}
