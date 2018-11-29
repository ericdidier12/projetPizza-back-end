package eu.busi.projetpizza.model;

import java.util.List;

public class Ingredient {

    private long id;
    private String name;
    private float recipe_qunatity;
    private float stock_quantity;
    private float unit_price;
    private float numberIngredient;
    List<Pizza> pizzas;

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public Ingredient(long id, String name, float recipe_qunatity, float stock_quantity, float unit_price) {
        this.id = id;
        this.name = name;
        this.recipe_qunatity = recipe_qunatity;
        this.stock_quantity = stock_quantity;
        this.unit_price = unit_price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getRecipe_qunatity() {
        return recipe_qunatity;
    }

    public void setRecipe_qunatity(float recipe_qunatity) {
        this.recipe_qunatity = recipe_qunatity;
    }

    public float getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(float stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public float getNumberIngredient() {
        return numberIngredient;
    }

    public void setNumberIngredient(float numberIngredient) {
        this.numberIngredient = numberIngredient;
    }

}
