package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.Comparator;
import java.util.List;

// Understands a connection to a specific Node
class Link {

    private final Node target;
    private final double cost;

    Link(Node target, double cost) {
        this.target = target;
        this.cost = cost;
    }

    Path path(Node destination, List<Node> visitedNodes, Comparator<Path> strategy) {
        return target.path(destination, visitedNodes, strategy).prepend(this);
    }

    static double totalCost(List<Link> links) {
        return links.stream()
                .mapToDouble(link -> link.cost)
                .sum();
    }
}
