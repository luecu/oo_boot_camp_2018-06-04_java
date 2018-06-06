package order;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

// Specifies protocols for using Order
public interface Orderable<T extends Orderable> {

    boolean isBetterThan(T other);

    static <T extends Orderable> T best(T ... elements) {
        T champion = null;
        for (T challenger : elements) {
            if (champion == null || challenger.isBetterThan(champion))
                champion = challenger;
        }
        return champion;
    }

}
