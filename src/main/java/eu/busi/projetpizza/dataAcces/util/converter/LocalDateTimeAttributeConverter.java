package eu.busi.projetpizza.dataAcces.util.converter;

/**
 * created by  eric.nyandwi on Nov,08/11/2018
 */
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return (localDateTime == null ? null : java.sql.Timestamp.valueOf(localDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp date) {
        return (date == null ? null : date.toLocalDateTime());
    }
}