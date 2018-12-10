
package eu.busi.projetpizza.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;

public class Pizza {

    private Long id;
    private String name;
    private float price;
    private boolean month_promo;
    private boolean fixed;
    private List<Order_Line> order_line;

    @Min(value = 1)
    @Digits(integer = 2, fraction = 0 ,message = "must be greater than or equal to 1 ")
    private Integer number = 1;
     private Category category;
    private List<Ingredient> ingredients;


    public Pizza() {
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Order_Line> getOrder_line() {
        return order_line;
    }

    public void setOrder_line(List<Order_Line> order_line) {
        this.order_line = order_line;
    }

    public Category getCategory(){
        return category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isMonth_promo() {
        return month_promo;
    }

    public void setMonth_promo(boolean month_promo) {
        this.month_promo = month_promo;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(getName(), pizza.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

