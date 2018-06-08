package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import quantity.IntervalQuantity;

import java.util.ArrayList;
import java.util.List;

// Understands a specific way from one Node to another Node
public abstract class Path {

    static final Path NONE = new NoPath();

    public abstract int hopCount();

    public abstract double cost();

    abstract void prepend(Link link);

    static class ActualPath extends Path {
        private final List<Link> links = new ArrayList<>();

        ActualPath() { super(); }

        @Override
        public int hopCount() {
            return links.size();
        }

        @Override
        public double cost() {
            return Link.totalCost(links);
        }

        @Override
        void prepend(Link link) {
            links.add(link);
        }
    }

    private static class NoPath extends Path {

        private NoPath() { super(); }

        @Override
        public int hopCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public double cost() {
            return Double.POSITIVE_INFINITY;
        }

        @Override
        void prepend(Link ignore) {} // No implementation required
    }
}
