package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import chance.Chance;
import order.Orderable;
import org.junit.jupiter.api.Test;
import rectangle.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

// Ensures the ordering algorithms work against all relevant Objects
public class OrderTest {

    @Test
    void rectangleWithLargestArea() {
        assertEquals(24, (Orderable.best(
                new Rectangle(2, 3), new Rectangle(4, 6), Rectangle.square(3)))
                .area());
        assertNull(Orderable.best());
    }

    @Test
    public void mostLikelyChance() {
        assertEquals(new Chance(0.75), Orderable.best(
                new Chance(0.5), new Chance(0.75), new Chance(0.25)
        ));
    }

}
