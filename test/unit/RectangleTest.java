package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import rectangle.Rectangle;

// Ensures rectangle.Rectangle operates correctly
class RectangleTest {

    @Test
    void area() {
        assertEquals(24.0, new Rectangle(4.0, 6.0).area());
    }

    @Test
    void perimeter() {
        assertEquals(20.0, new Rectangle(4.0, 6.0).perimeter());
    }

    @Test
    void dimensionError() {
        assertThrows(IllegalArgumentException.class, ()-> new Rectangle(0, 6.0));
        assertThrows(IllegalArgumentException.class, ()-> new Rectangle(4.0, 0.0));
    }
}
