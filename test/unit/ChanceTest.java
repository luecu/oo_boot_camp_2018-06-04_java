package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import chance.Chance;

import java.util.Arrays;
import java.util.HashSet;

// Ensures that Chance works correctly
class ChanceTest {

    private final static Chance IMPOSSIBLE = new Chance(0.0);
    private final static Chance UNLIKELY = new Chance(0.25);
    private final static Chance EQUALLY_LIKELY = new Chance(0.50);
    private final static Chance LIKELY = new Chance(0.75);
    private final static Chance CERTAIN = new Chance(1.0);

    @Test
    void equality() {
        assertEquals(LIKELY, new Chance(0.75));
        assertNotEquals(LIKELY, new Chance(0.25));
        assertNotEquals(LIKELY, new Object());
        assertNotEquals(LIKELY, null);

    }

    @Test
    void polymorphism() {
        assertEquals(1, new HashSet<>(Arrays.asList(LIKELY, new Chance(0.75))).size());
        assertTrue(new HashSet<>(Arrays.asList(new Chance(0.75), new Chance(0.75))).contains(LIKELY));
    }

    @Test
    void hash() {
        assertEquals(LIKELY.hashCode(), new Chance(0.75).hashCode());
    }
}
