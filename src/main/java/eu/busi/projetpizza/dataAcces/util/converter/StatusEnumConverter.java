package eu.busi.projetpizza.dataAcces.util.converter;

import eu.busi.projetpizza.enums.EnumUtils;
import eu.busi.projetpizza.enums.StatusEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * created by  eric.nyandwi on Nov,08/11/2018
 */

/**
 * Convertisseur JPA qui associe une {@link StatusEnum ) à une colonne DB {@link Integer}.
 * Created by eric.nyandwi .
 */
@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<StatusEnum, Integer> {


    @Override
    public Integer convertToDatabaseColumn(StatusEnum statusEnum) {
        return statusEnum != null ? statusEnum.getValue() : null;
    }

    @Override
     public StatusEnum convertToEntityAttribute(Integer dbData) {
        return dbData != null ? EnumUtils.findEnumByValue(StatusEnum.class, dbData) : null;
    }
}
