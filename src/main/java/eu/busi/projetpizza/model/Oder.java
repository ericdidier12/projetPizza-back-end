package eu.busi.projetpizza.model;

import eu.busi.projetpizza.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * created by  eric.nyandwi on Nov,08/11/2018
 */

public class Oder {
    private LocalDateTime date_order;
    private Long id;
    private float delivery_price ;
    private float full_price;
    private float total_price;
    private boolean is_paid;
    private Collection<Promo> promos ;
    private StatusEnum statusEnum ;
    private User user;

    public Oder() {
    }

    public void setPromos(Collection<Promo> promos) {
        this.promos = promos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate_order() {
        return date_order;
    }

    public void setDate_order(LocalDateTime date_order) {
        this.date_order = date_order;
    }

    public float getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(float delivery_price) {
        this.delivery_price = delivery_price;
    }

    public float getFull_price() {
        return full_price;
    }

    public void setFull_price(float full_price) {
        this.full_price = full_price;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Promo> getPromos() {
        return promos;
    }
}
