package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import chance.Chance;

// Ensures that Chance works correctly
class ChanceTest {

    @Test
    void equality() {
        assertEquals(new Chance(0.75), new Chance(0.75));
        assertNotEquals(new Chance(0.75), new Chance(0.25));
        assertNotEquals(new Chance(0.75), new Object());
        assertNotEquals(new Chance(0.75), null);

    }
}
