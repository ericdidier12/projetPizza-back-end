package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.IngredientEntity;
import eu.busi.projetpizza.dataAcces.entity.PizzaEntity;
import eu.busi.projetpizza.model.Ingredient;
import eu.busi.projetpizza.model.Pizza;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import  static eu.busi.projetpizza.dataAcces.util.CategorieConveterTest.*;
import static org.junit.Assert.assertEquals;

/**
 * created by  eric.nyandwi on Nov,15/11/2018
 */
public class PizzaConverterTest {

    public static final String NAME = "eric Nyandwi";
    public static final boolean ISFIXE = true;
    public static  boolean ISMONTH_PROMO  =  false;
    public static final float PRICE  = 6.7f;
    public static final  String CATEGORY = "normal";
    public   Ingredient ingredient1 = new Ingredient(1L, "sauce tomate", 50.0f, 2000.0f, 1.5f);
    public   Ingredient ingredient2 = new Ingredient(2l, "Soumon",50.5f, 3000.0f, 1.5f);
    public   Ingredient ingredient3 = new Ingredient(3l, "gorg", 50.0f, 2000.0f, 1.5f);
    public  static List<Ingredient> ingredienties ;


    @Before
    public void initialize() {
        ingredienties  = new ArrayList<>();

        ingredienties.add(ingredient1);
        ingredienties.add(ingredient2);
        ingredienties.add(ingredient3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPizzaEntityNullToPizzaModel() {
        PizzaConveter.pizzaEntityTopizzaModel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPizzaModelToPizzaEntity() {
        UserConverter.userModelToUserEntity(null);
    }

    @Test
    public void testPizzaEntityAllFields() {
        PizzaEntity pizzaEntity = createPizzaEntityAllFields();

        Pizza pizza = PizzaConveter.pizzaEntityTopizzaModel(pizzaEntity);
        assertEquals(NAME, pizza.getName());
        assertEquals(ISFIXE, pizza.isFixed());
        assertEquals(PRICE, pizza.getPrice(),6.7f );
        assertEquals(CATEGORY, CategorieConveterTest.NAME_1 );
        assertEquals(3


                , pizza.getIngredients().size());
    }


    @Test
    public void testToPizzaAllFields() {
        Pizza pizza = new Pizza();
        pizza.setName(NAME);
        pizza.setFixed(ISFIXE);
        pizza.setMonth_promo(ISMONTH_PROMO);
        pizza.setCategory(createCategory());
        pizza.setIngredients(ingredienties);
        PizzaEntity pizzaEntity   = PizzaConveter.pizzaModelTopizzaEntity(pizza);
        validateUserEntity(pizzaEntity);
    }

    /**
     * Valide les propriétés de l'entité {@link Pizza} .
     * L'ensemble des propriétés communes est testé not null.
     *
     * @param pizzaEntity
     */
    public static void validateUserEntity(PizzaEntity pizzaEntity) {
        assertEquals(NAME, pizzaEntity.getName());
        assertEquals(ISFIXE, pizzaEntity.isFixed());
        assertEquals(ISMONTH_PROMO, pizzaEntity.isMonth_promo());
        assertEquals(CATEGORY, pizzaEntity.getCategoryEntity().getName());
        assertEquals( 3, pizzaEntity.getIngredientEntityList().size());

    }


    /**
     * Création d'une entité {@link eu.busi.projetpizza.dataAcces.entity.PizzaEntity} de base commune au tests de cette classe et de {@link eu.busi.projetpizza.model.Pizza}.
     * Toutes les propriétés sont remplies.
     *
     * @return
     */
    public static PizzaEntity createPizzaEntityAllFields() {
        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setName(NAME);
        pizzaEntity.setFixed(ISFIXE);
        pizzaEntity.setMonth_promo(ISMONTH_PROMO);
        pizzaEntity.setCategoryEntity(CategorieConveterTest.createCategoryEnytiy());
        List<IngredientEntity> ingredientEntityList = new ArrayList<>();

        for (Ingredient ingredient:ingredienties) {
            ingredientEntityList.add(IngredientConveter.ingredientIngredientToIngredientEntity(ingredient));
        }
        pizzaEntity.setIngredientEntityList(ingredientEntityList);

        return pizzaEntity;
    }




}