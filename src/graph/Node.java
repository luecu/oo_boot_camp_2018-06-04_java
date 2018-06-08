package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import static graph.Link.*;
import static graph.Path.ActualPath;

// Understands its links
public class Node {
    private final static double UNREACHABLE = Double.POSITIVE_INFINITY;
    private final List<Link> links = new ArrayList<>();

    public boolean canReach(Node destination) {
        return this.cost(destination, noVisitedNodes(), LEAST_COST) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        return (int)cost(destination, FEWEST_HOPS);
    }

    public double cost(Node destination) {
        return cost(destination, LEAST_COST);
    }

    private double cost(Node destination, CostStrategy strategy) {
        double result = this.cost(destination, noVisitedNodes(), strategy);
        if (result == UNREACHABLE) throw new IllegalArgumentException("Unreachable destination");
        return result;
    }

    double cost(Node destination, List<Node> visitedNodes, CostStrategy strategy) {
        if (this == destination) return 0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        return links.stream()
                .mapToDouble(link -> link.cost(destination, copyWithThis(visitedNodes), strategy))
                .reduce(UNREACHABLE, Math::min);
    }

    // Uses an inner class static initializer (hence the double braces)
    private List<Node> copyWithThis(List<Node> original) {
        return new ArrayList<>(original) {{ add(Node.this); }};
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }

    public LinkBuilder cost(double amount) {
        return new LinkBuilder(amount, links);
    }

    public Path path(Node destination) {
        Path result = this.path(destination, noVisitedNodes(), Path.LEAST_COST);
        if (result == Path.NONE) throw new IllegalArgumentException("Unreachable destination");
        return result;
    }

    Path path(Node destination, List<Node> visitedNodes, Comparator<Path> strategy) {
        if (this == destination) return new ActualPath();
        if (visitedNodes.contains(this)) return Path.NONE;
        return links.stream()
                .map(link -> link.path(destination, copyWithThis(visitedNodes), strategy))
                .filter(Objects::nonNull)
                .min(strategy)
                .orElse(Path.NONE);
    }

    public static class LinkBuilder {

        private final double cost;
        private final List<Link> links;

        private LinkBuilder(double cost, List<Link> links) {
            this.cost = cost;
            this.links = links;
        }

        public Node to(Node target) {
            links.add(new Link(target, cost));
            return target;
        }
    }
}
