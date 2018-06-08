package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


// Understands a specific way from one Node to another Node
public class Path {
    static final Comparator<Path> LEAST_COST = Comparator.comparing(Path::cost);
    static final Comparator<Path> FEWEST_HOPS = Comparator.comparing(Path::hopCount);

    private final List<Link> links = new ArrayList<>();

    Path() { super(); }

    public int hopCount() {
        return links.size();
    }

    public double cost() {
        return Link.totalCost(links);
    }

    void prepend(Link link) {
        links.add(link);
    }
}
