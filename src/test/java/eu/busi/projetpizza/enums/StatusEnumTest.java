package eu.busi.projetpizza.enums;

import org.junit.Test;
import static  eu.busi.projetpizza.enums.EnumUtils.*;
import static org.junit.Assert.*;

/**
 * created by  eric.nyandwi on Nov,08/11/2018
 */
public class StatusEnumTest {
    private static final Integer ILLEGAL_VALUE = -1;

    @Test
    public void fromValue() {
        assertEquals(StatusEnum.IN_PROGRESS, findEnumByValue(StatusEnum.class, 1));
        assertEquals(StatusEnum.READY, findEnumByValue(StatusEnum.class, 2));
        assertEquals(StatusEnum.TRANSIT, findEnumByValue(StatusEnum.class, 3));
        assertEquals(StatusEnum.DELIVERD, findEnumByValue(StatusEnum.class, 4));

        assertEquals(4, StatusEnum.values().length);

        try {
            findEnumByValue(StatusEnum.class, ILLEGAL_VALUE);
            fail("should have failed...");
        } catch (IllegalArgumentException e) {

        }
    }
}