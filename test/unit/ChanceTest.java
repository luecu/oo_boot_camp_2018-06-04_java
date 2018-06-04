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

    @Test
    void not() {
        assertEquals(UNLIKELY, LIKELY.not());
        assertEquals(LIKELY, LIKELY.not().not());
        assertEquals(CERTAIN, IMPOSSIBLE.not());
        assertEquals(EQUALLY_LIKELY, EQUALLY_LIKELY.not());
    }

    @Test
    void and() {
        assertEquals(UNLIKELY, EQUALLY_LIKELY.and(EQUALLY_LIKELY));
        assertEquals(new Chance(0.1875), LIKELY.and(UNLIKELY));
        assertEquals(LIKELY.and(UNLIKELY), UNLIKELY.and(LIKELY));
        assertEquals(IMPOSSIBLE, LIKELY.and(IMPOSSIBLE));
        assertEquals(LIKELY, CERTAIN.and(LIKELY));
    }

    @Test
    void or() {
        assertEquals(LIKELY, EQUALLY_LIKELY.or(EQUALLY_LIKELY));
        assertEquals(new Chance(0.8125), LIKELY.or(UNLIKELY));
        assertEquals(LIKELY.or(UNLIKELY), UNLIKELY.or(LIKELY));
        assertEquals(LIKELY, LIKELY.or(IMPOSSIBLE));
        assertEquals(CERTAIN, CERTAIN.or(LIKELY));
    }
}
