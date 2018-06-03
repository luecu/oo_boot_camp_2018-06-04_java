package unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import rectangle.Rectangle;

// Ensures rectangle.Rectangle operates correctly
class RectangleTest {

    @Test
    void area() {
        assertEquals(24, new Rectangle(4.0, 6.0).area());
    }
}
