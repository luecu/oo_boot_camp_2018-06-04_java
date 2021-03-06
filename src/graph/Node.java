package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static graph.Path.*;

// Understands its links
public class Node {

    private final List<Link> links = new ArrayList<>();

    public boolean canReach(Node destination) {
        return !paths(destination).isEmpty();
    }

    public int hopCount(Node destination) {
        return path(destination, FEWEST_HOPS).hopCount();
    }

    public double cost(Node destination) {
        return path(destination).cost();
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
        return path(destination, Path.LEAST_COST);
    }

    private Path path(Node destination, Comparator<Path> strategy) {
        List<Path> paths = this.paths(destination, noVisitedNodes());
        if (paths.isEmpty()) throw new IllegalArgumentException("Unreachable destination");
        return Collections.min(paths, strategy);
    }

    public List<Path> paths(Node destination) {
        return paths(destination, noVisitedNodes());
    }

    List<Path> paths(Node destination, List<Node> visitedNodes) {
        if (this == destination) return Collections.singletonList(new Path());
        if (visitedNodes.contains(this)) return Collections.emptyList();
        List<Path> results = new ArrayList<>();
        for(Link link:links)
            results.addAll(link.paths(destination, copyWithThis(visitedNodes)));
        return results;
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
