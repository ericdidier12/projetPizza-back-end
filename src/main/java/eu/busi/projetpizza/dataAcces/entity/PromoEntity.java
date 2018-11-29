package eu.busi.projetpizza.dataAcces.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PromoEntity est la classe représentant le promo d'un
 * created by  eric.nyandwi on Nov,08/11/2018
 */

@Entity
@Table(name = "promo_code")
public class PromoEntity extends  BaseEntity {

    private String name;
    private float reduction ;

    public PromoEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getReduction() {
        return reduction;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }
}
