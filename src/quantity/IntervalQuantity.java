package quantity;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import order.Orderable;

// Understands a specific zero-based measurement
public class IntervalQuantity implements Orderable<IntervalQuantity> {
    private static final double DELTA = 0.00000001;

    final double amount;
    final Unit unit;

    IntervalQuantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof IntervalQuantity && this.equals((IntervalQuantity)other);
    }

    private boolean equals(IntervalQuantity other) {
        return this.isCompatible(other)
                && Math.abs(this.amount - convertedAmount(other)) < DELTA;
    }

    private boolean isCompatible(IntervalQuantity other) {
        return this.unit.isCompatible(other.unit);
    }

    double convertedAmount(IntervalQuantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }

    @Override
    public boolean isBetterThan(IntervalQuantity other) {
        return this.amount > convertedAmount(other);
    }
}
