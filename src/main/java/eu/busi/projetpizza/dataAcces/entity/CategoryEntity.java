package eu.busi.projetpizza.dataAcces.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private Set<PizzaEntity> pizzaEntities;

    public CategoryEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PizzaEntity> getPizzaEntities() {
        return pizzaEntities;
    }

    public void setPizzaEntities(Set<PizzaEntity> pizzaEntities) {
        this.pizzaEntities = pizzaEntities;
    }
}
