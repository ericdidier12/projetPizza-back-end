package eu.busi.projetpizza.dataAcces.entity;

import eu.busi.projetpizza.dataAcces.util.converter.LocalDateTimeAttributeConverter;
import eu.busi.projetpizza.dataAcces.util.converter.StatusEnumConverter;
import eu.busi.projetpizza.enums.StatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * created by  eric.nyandwi on Nov,08/11/2018
 */
@Entity
@Table(name = "order_command")
public class OderEntity extends BaseEntity {

    @Column(nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime date_order;
    private float delivery_price ;
    private float full_price;
    private float total_price;
    private boolean is_paid;

    @OneToMany(mappedBy = "oderEntity")
    private List<OrderLineEntity> orderlineentities;

    @OneToMany
    @JoinColumn(name = "promo_code_id", referencedColumnName = "id")
    private Collection<PromoEntity>  promoEntity ;

    @Column(name = "statut_id", nullable = false, length = 2)
    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum statusEnum ;

    @ManyToOne
    @JoinColumn(name="client_id")
    private UserEntity userEntity;

    public List<OrderLineEntity> getOrderLineEntities() {
        return orderlineentities;
    }

    public void setOrderLineEntities(List<OrderLineEntity> orderLineEntities) {
        this.orderlineentities = orderLineEntities;
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

    public Collection<PromoEntity> getPromoEntity() {
        return promoEntity;
    }

    public void setPromoEntity(Collection<PromoEntity> promoEntity) {
        this.promoEntity = promoEntity;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
