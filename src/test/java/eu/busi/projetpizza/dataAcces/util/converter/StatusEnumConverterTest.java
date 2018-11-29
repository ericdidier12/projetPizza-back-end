package eu.busi.projetpizza.dataAcces.util.converter;

import org.junit.Before;
import org.junit.Test;
import static eu.busi.projetpizza.enums.StatusEnum.*;

import static org.junit.Assert.*;

/**
 * created by  eric.nyandwi on Nov,08/11/2018
 */
public class StatusEnumConverterTest {
    private static final Integer ILLEGAL_VALUE = -1;
    private static final String ERROR = "La conversion est incorrecte.";

    private StatusEnumConverter statusEnumConverter;

    @Before
    public void setUp() {
        statusEnumConverter = new StatusEnumConverter();
    }

    @Test
    public void testConvertToDatabaseColumn() {
        assertNull(ERROR, statusEnumConverter.convertToDatabaseColumn(null));
        assertEquals(ERROR, IN_PROGRESS.getValue(), statusEnumConverter.convertToDatabaseColumn(IN_PROGRESS));
        assertEquals(ERROR, READY.getValue(), statusEnumConverter.convertToDatabaseColumn(READY));
        assertEquals(ERROR, TRANSIT.getValue(), statusEnumConverter.convertToDatabaseColumn(TRANSIT));

    }

    @Test
    public void testConvertToEntityAttribute() {
        assertNull(ERROR, statusEnumConverter.convertToEntityAttribute(null));
        assertEquals(ERROR, IN_PROGRESS, statusEnumConverter.convertToEntityAttribute(IN_PROGRESS.getValue()));
        assertEquals(ERROR, READY, statusEnumConverter.convertToEntityAttribute(READY.getValue()));
        assertEquals(ERROR, TRANSIT, statusEnumConverter.convertToEntityAttribute(TRANSIT.getValue()));

        try {
            statusEnumConverter.convertToEntityAttribute(ILLEGAL_VALUE);
            fail("Should have failed...");
        } catch (Exception e) {
        }
    }

}