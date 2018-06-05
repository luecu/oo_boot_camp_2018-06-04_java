package quantity;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

// Understands a specific zero-based measurement
public class RatioQuantity {
    private static final double DELTA = 0.00000001;

    private final double amount;
    private final Unit unit;

    RatioQuantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof RatioQuantity && this.equals((RatioQuantity)other);
    }

    private boolean equals(RatioQuantity other) {
        return this.isCompatible(other)
                && Math.abs(this.amount - convertedAmount(other)) < DELTA;
    }

    private boolean isCompatible(RatioQuantity other) {
        return this.unit.isCompatible(other.unit);
    }

    private double convertedAmount(RatioQuantity other) {
        return this.unit.convertedAmount(other.amount, other.unit);
    }

    @Override
    public int hashCode() {
        return unit.hashCode(amount);
    }
    
    public RatioQuantity plus(RatioQuantity other) {
        return new RatioQuantity(this.amount + convertedAmount(other), unit);
    }

    public RatioQuantity negate() {
        return new RatioQuantity(-amount, unit);
    }

    public RatioQuantity minus(RatioQuantity other) {
        return this.plus(other.negate());
    }
}
