package rectangle;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import order.Orderable;

// Understands a four-sided polygon with sides at right angles
public class Rectangle implements Orderable<Rectangle> {

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Dimensions must be positive");
        this.width = width;
        this.height = height;
    }

    public static Rectangle square(double side) { return new Rectangle(side, side); }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public boolean isBetterThan(Rectangle other) {
        return this.area() > other.area();
    }
}
