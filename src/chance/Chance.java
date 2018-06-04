package chance;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

public class Chance {
    private static final double CERTAIN_FRACTION = 1.0;
    private final double fraction;

    public Chance(double likelihoodAsFraction) {
        fraction = likelihoodAsFraction;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other != null
                && other.getClass() == Chance.class
                && this.equals((Chance) other);
    }

    private boolean equals(Chance other) {
        return this.fraction == other.fraction;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(fraction);
    }

    public Chance not() {
        return new Chance(CERTAIN_FRACTION - fraction);
    }
}
