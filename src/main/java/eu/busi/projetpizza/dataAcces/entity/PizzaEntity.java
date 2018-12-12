package eu.busi.projetpizza.dataAcces.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pizza")
public class PizzaEntity extends BaseEntity{


    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @Column(name = "month_promo")
    private boolean  month_promo;
    @Column(name = "fixed")
    private boolean fixed;

    @OneToMany(mappedBy = "pizzaOrderLineEntity")
    private List<OrderLineEntity> orderLineEntitiesList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_id")
    private CategoryEntity categoryEntity;

    @ManyToMany(mappedBy = "pizzaEntitiesList", fetch = FetchType.EAGER)
    private List<IngredientEntity> ingredientEntityList;

    @Transient
    private int number2;

    public PizzaEntity() {
    }

    public List<OrderLineEntity> getOrderLineEntitiesList() {
        return orderLineEntitiesList;
    }
    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public List<IngredientEntity> getIngredientEntityList() {
        return ingredientEntityList;
    }

    public void setIngredientEntityList(List<IngredientEntity> ingredientEntityList) {
        this.ingredientEntityList = ingredientEntityList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaEntity)) return false;
        if (!super.equals(o)) return false;
        PizzaEntity that = (PizzaEntity) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
