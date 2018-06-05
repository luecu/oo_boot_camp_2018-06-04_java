package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static quantity.Unit.*;

// Ensures Quantities operate correctly
class QuantityTest {

    @Test
    void equalityOfLikeUnits() {
        assertEquals(TABLESPOON.s(6), TABLESPOON.s(6));
        assertNotEquals(TABLESPOON.s(6), TABLESPOON.s(4));
        assertNotEquals(TABLESPOON.s(6), new Object());
        assertNotEquals(TABLESPOON.s(6), null);
    }
    
    @Test
    void equalityOfUnlikeUnits() {
        assertEquals(OUNCE.s(4), CUP.s(0.5));
        assertEquals(TEASPOON.s(768), GALLON.s(1));
        assertNotEquals(TABLESPOON.s(6), OUNCE.s(6));
    }
}
