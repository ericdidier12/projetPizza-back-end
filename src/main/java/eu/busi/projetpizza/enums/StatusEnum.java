package eu.busi.projetpizza.enums;


import eu.busi.projetpizza.dataAcces.util.EnumValuePizzaria;

/**
 * Code permettant de distinguer les différentes sortes de status.
 * <br/>
 * created by  eric.nyandwi on Nov,08/11/2018
 */

public enum StatusEnum implements EnumValuePizzaria<Integer> {

    IN_PROGRESS(1),
    READY(2),
    TRANSIT(3),
    DELIVERD(4),;

    private Integer value;

    StatusEnum(Integer value) { this.value = value; }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
