package order;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

// Specifies protocols for using Order
public interface Orderable<T extends Orderable> {

    boolean isBetterThan(T other);

    static <S extends Orderable<? super S>> S best(S ... elements) {
        S champion = null;
        for (S challenger : elements) {
            if (champion == null || challenger.isBetterThan(champion))
                champion = challenger;
        }
        return champion;
    }

}
