package graph;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import java.util.ArrayList;
import java.util.List;

// Understands a specific way from one Node to another Node
public class Path {

    private final List<Link> links = new ArrayList<>();

    public int hopCount() {
        return links.size();
    }

    public double cost() {
        return Link.totalCost(links);
    }

    void prepend(Link link) {
        links.add(0, link);
    }
}
