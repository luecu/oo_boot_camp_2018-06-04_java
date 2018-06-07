package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.ArrayList;
import java.util.List;

// Understands its links
public class Node {
    private final static double UNREACHABLE = Double.POSITIVE_INFINITY;
    private final List<Link> links = new ArrayList<>();

    public Node to(Node neighbor) {
        links.add(new Link(neighbor));
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return this.hopCount(destination, noVisitedNodes()) != UNREACHABLE;
    }

    public int hopCount(Node destination) {
        double result = this.hopCount(destination, noVisitedNodes());
        if (result == UNREACHABLE) throw new IllegalArgumentException("Unreachable destination");
        return (int)result;
    }

    double hopCount(Node destination, List<Node> visitedNodes) {
        if (this == destination) return 0;
        if (visitedNodes.contains(this)) return UNREACHABLE;
        return links.stream()
                .mapToDouble(link -> link.hopCount(destination, copyWithThis(visitedNodes)))
                .reduce(UNREACHABLE, Math::min);
    }

    // Uses an inner class static initializer (hence the double braces)
    private List<Node> copyWithThis(List<Node> original) {
        return new ArrayList<>(original) {{ add(Node.this); }};
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
