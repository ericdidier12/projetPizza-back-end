package eu.busi.projetpizza.dataAcces.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredient")
public class IngredientEntity extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "recipe_quantity")
    private float recipe_quantity;
    @Column(name = "stock_quantity")
    private float stock_quantity;
    @Column(name = "unit_price")
    private float unit_price;

    @ManyToMany
    @JoinTable(name="recipe", joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<PizzaEntity> pizzaEntitiesList;

   /* *//**Liaison Order order line**//*
    @OneToMany(mappedBy = "oderOrderLineEntity", cascade = CascadeType.ALL)
    private Set<OrderLineEntity> orderLineEntities;*/

    public IngredientEntity() {
    }

   /* public Set<OrderLineEntity> getOrderLineEntities() {
        return orderLineEntities;
    }

    public void setOrderLineEntities(Set<OrderLineEntity> orderLineEntities) {
        this.orderLineEntities = orderLineEntities;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRecipe_quantity() {
        return recipe_quantity;
    }

    public void setRecipe_quantity(float recipe_quantity) {
        this.recipe_quantity = recipe_quantity;
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

    public List<PizzaEntity> getPizzaEntitiesList() {
        return pizzaEntitiesList;
    }

    public void setPizzaEntitiesList(List<PizzaEntity> pizzaEntitiesList) {
        this.pizzaEntitiesList = pizzaEntitiesList;
    }
}
