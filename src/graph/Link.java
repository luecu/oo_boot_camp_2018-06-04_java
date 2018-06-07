package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.List;

// Understands a connection to a specific Node
class Link {
    private final Node target;
    private final double cost;

    Link(Node target, double cost) {
        this.target = target;
        this.cost = cost;
    }

    double hopCount(Node destination, List<Node> visitedNodes) {
        return target.hopCount(destination, visitedNodes) + 1;
    }
}
