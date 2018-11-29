package eu.busi.projetpizza.dataAcces.util;

import eu.busi.projetpizza.dataAcces.entity.PromoEntity;
import eu.busi.projetpizza.model.Promo;

/**
 *
 * <b> Transformation entre le modèle des entités JPA et les objetsModel . </b>
 * created by  eric.nyandwi on Nov,08/11/2018
 *
 */
public class PromoConverter {

    /**
     * Transforme une entité JPA {@link PromoEntity} en objet Model {@link Promo}.
     * @param promoEntity
     * @return Objet type Promo
     */
    public static Promo promoEntityToPromoModel(PromoEntity promoEntity){
        if (promoEntity == null) {
            throw new IllegalArgumentException(" objet promoEntity  ne peut pas être null ");
        }
        Promo promo = new Promo();
        promo.setId(promoEntity.getId());
        promo.setName(promoEntity.getName() != null ? promoEntity.getName() : null);
        promo.setReduction(promoEntity.getReduction());
        return  promo;
    }

    /**
     * Transforme un objet Model {@link Promo} en une entité JPA {@link PromoEntity}.
     * @param promo
     * @return objet promoEntity
     */
    public static PromoEntity promoModelToPromoEntity (Promo promo) {
        if (promo == null) {
            throw new IllegalArgumentException(" objet promo  ne peut pas être null ");
        }
        PromoEntity promoEntity = new PromoEntity();
        promoEntity.setId(promo.getId());
        promoEntity.setName(promo.getName() != null ? promo.getName() : null);
        promoEntity.setReduction(promo.getReduction());
        return promoEntity;
    }
}
