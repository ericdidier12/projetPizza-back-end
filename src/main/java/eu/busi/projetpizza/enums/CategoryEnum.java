package eu.busi.projetpizza.enums;


import eu.busi.projetpizza.dataAcces.util.EnumValuePizzaria;



public enum CategoryEnum implements EnumValuePizzaria<Integer> {

    NORMAL(1),
    AMERICAN(2),
    OF_THE_SEA(3),;

    private Integer value;

    CategoryEnum(Integer value) { this.value = value; }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
