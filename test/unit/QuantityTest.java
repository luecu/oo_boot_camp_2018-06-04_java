package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

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


    @Test
    void polymorphism() {
        assertEquals(1, new HashSet<>(Arrays.asList(TABLESPOON.s(4), TABLESPOON.s(4))).size());
        assertEquals(1, new HashSet<>(Arrays.asList(TABLESPOON.s(4), OUNCE.s(2))).size());
        assertTrue(new HashSet<>(Arrays.asList(TABLESPOON.s(4), OUNCE.s(2))).contains(TEASPOON.s(12)));
    }

    @Test
    void hash() {
        assertEquals(TABLESPOON.s(4).hashCode(), TABLESPOON.s(4).hashCode());
        assertEquals(TABLESPOON.s(4).hashCode(), OUNCE.s(2).hashCode());
    }

    @Test
    void arithmetic() {
        assertEquals(QUART.s(0.5), TABLESPOON.s(6).plus(OUNCE.s(13)));
        assertEquals(TABLESPOON.s(-6), TABLESPOON.s(6).negate());
        assertEquals(PINT.s(-0.5), TABLESPOON.s(10).minus(OUNCE.s(13)));
    }
}
