package quantity;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

// Understands a specific measurement
public class Quantity {
    public static final Object TEASPOON = new Object();
    public static final Object TABLESPOON = new Object();
    public static final Object OUNCE = new Object();
    public static final Object CUP = new Object();
    public static final Object PINT = new Object();
    public static final Object QUART = new Object();
    public static final Object GALLON = new Object();

    private final double amount;
    private final Object unit;

    public Quantity(double amount, Object unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof Quantity && this.equals((Quantity)other);
    }

    private boolean equals(Quantity other) {
        return this.amount == other.amount && this.unit == other.unit;
    }
}
