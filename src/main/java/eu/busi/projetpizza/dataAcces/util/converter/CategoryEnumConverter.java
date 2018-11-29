package eu.busi.projetpizza.dataAcces.util.converter;

import eu.busi.projetpizza.enums.CategoryEnum;
import eu.busi.projetpizza.enums.EnumUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class CategoryEnumConverter implements AttributeConverter<CategoryEnum, Integer> {


    @Override
    public Integer convertToDatabaseColumn(CategoryEnum categoryEnum) {
        return categoryEnum != null ? categoryEnum.getValue() : null;
    }

    @Override
     public CategoryEnum convertToEntityAttribute(Integer dbData) {
        return dbData != null ? EnumUtils.findEnumByValue(CategoryEnum.class, dbData) : null;
    }
}
