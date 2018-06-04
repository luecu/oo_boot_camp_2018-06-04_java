package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import quantity.Quantity;
import static quantity.Quantity.*;

// Ensures Quantities operate correctly
class QuantityTest {

    @Test
    void equalityOfLikeUnits() {
        assertEquals(new Quantity(6, TABLESPOON), new Quantity(6, TABLESPOON));
        assertNotEquals(new Quantity(6, TABLESPOON), new Quantity(4, TABLESPOON));
        assertNotEquals(new Quantity(6, TABLESPOON), new Object());
        assertNotEquals(new Quantity(6, TABLESPOON), null);
    }
    
    @Test
    void equalityOfUnlikeUnits() {
        assertNotEquals(new Quantity(6, TABLESPOON), new Quantity(6, OUNCE));
    }
}
