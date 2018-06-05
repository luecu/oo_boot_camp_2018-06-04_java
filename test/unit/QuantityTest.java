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
        assertEquals(ROD.s(1), FOOT.s(6.6));
        assertEquals(FOOT.s(66), CHAIN.s(1));
        assertNotEquals(TABLESPOON.s(6), OUNCE.s(6));
        assertEquals(MILE.s(1), INCH.es(12 * 5280));
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
        assertEquals(FOOT.s(330).hashCode(), FURLONG.s(0.5).hashCode());
        assertEquals(CELSIUS.es(10).hashCode(), FAHRENHEIT.s(50).hashCode());
    }

    @Test
    void arithmetic() {
        assertEquals(QUART.s(0.5), TABLESPOON.s(6).plus(OUNCE.s(13)));
        assertEquals(TABLESPOON.s(-6), TABLESPOON.s(6).negate());
        assertEquals(PINT.s(-0.5), TABLESPOON.s(10).minus(OUNCE.s(13)));
        assertEquals(FOOT.s(-4), INCH.es(24).minus(YARD.s(2)));
    }

    @Test
    void crossMetricType()  {
        assertNotEquals(INCH.es(1), TEASPOON.s(1));
        assertNotEquals(OUNCE.s(4), FOOT.s(2));
    }

    @Test
    void mixedUnitArithmetic() {
        assertThrows(IllegalArgumentException.class, () ->
                YARD.s(3).minus(TABLESPOON.s(4)));
    }

    @Test
    void temperature() {
        assertEquals(CELSIUS.es(0), FAHRENHEIT.s(32));
        assertEquals(FAHRENHEIT.s(32), CELSIUS.es(0));
        assertEquals(CELSIUS.es(-40), FAHRENHEIT.s(-40));
        assertEquals(FAHRENHEIT.s(-40), CELSIUS.es(-40));
        assertEquals(CELSIUS.es(10), FAHRENHEIT.s(50));
        assertEquals(FAHRENHEIT.s(50), CELSIUS.es(10));
        assertEquals(CELSIUS.es(100), FAHRENHEIT.s(212));
        assertEquals(FAHRENHEIT.s(212), CELSIUS.es(100));
    }

    @Test
    void temperatureArithmetic() {
        // The following should not compile!
//         CELSIUS.es(10).minus(FAHRENHEIT.s(32));
    }
}
