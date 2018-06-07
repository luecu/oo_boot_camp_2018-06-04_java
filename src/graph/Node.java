package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.ArrayList;
import java.util.List;

// Understands its neighbors
public class Node {
    private final List<Node> neighbors = new ArrayList<>();

    public Node to(Node neighbor) {
        neighbors.add(neighbor);
        return neighbor;
    }

    public boolean canReach(Node destination) {
        return this.canReach(destination, noVisitedNodes());
    }

    private boolean canReach(Node destination, List<Node> visitedNodes) {
        if (this == destination) return true;
        if (visitedNodes.contains(this)) return false;
        visitedNodes.add(this);
        return neighbors.stream()
                .anyMatch(n -> n.canReach(destination, visitedNodes));
    }

    private List<Node> noVisitedNodes() {
        return new ArrayList<>();
    }
}
