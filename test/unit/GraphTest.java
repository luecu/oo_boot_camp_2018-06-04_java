package unit;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

import graph.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import graph.Node;

// Ensures various graph APIs work as expected
class GraphTest {
    private final static double EPSILON = 0.00000000001;

    private final static Node A, B, C, D, E, F, G;

    static {
        A = new Node();
        B = new Node();
        C = new Node();
        D = new Node();
        E = new Node();
        F = new Node();
        G = new Node();

        B.cost(5).to(A);
        B.cost(4).to(C).cost(6).to(D).cost(2).to(E).cost(3).to(B).cost(8).to(F);
        C.cost(1).to(D);
        C.cost(7).to(E);
    }

    @Test
    void canReach() {
        assertTrue(B.canReach(B));
        assertTrue(B.canReach(A));
        assertTrue(B.canReach(F));
        assertTrue(B.canReach(D));
        assertTrue(C.canReach(F));
        assertFalse(G.canReach(B));
        assertFalse(A.canReach(B));
        assertFalse(B.canReach(G));
    }

    @Test
    void hopCount() {
        assertEquals(0, B.hopCount(B));
        assertEquals(1, B.hopCount(A));
        assertEquals(1, B.hopCount(F));
        assertEquals(2, B.hopCount(D));
        assertEquals(3, C.hopCount(F));
        assertThrows(IllegalArgumentException.class, () -> G.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> A.hopCount(B));
        assertThrows(IllegalArgumentException.class, () -> B.hopCount(G));
    }

    @Test
    void cost() {
        assertEquals(0, B.cost(B));
        assertEquals(5, B.cost(A));
        assertEquals(8, B.cost(F));
        assertEquals(5, B.cost(D));
        assertEquals(14, C.cost(F));
        assertThrows(IllegalArgumentException.class, () -> G.cost(B));
        assertThrows(IllegalArgumentException.class, () -> A.cost(B));
        assertThrows(IllegalArgumentException.class, () -> B.cost(G));
    }

    @Test
    void path() {
        assertPath(A, A, 0, 0);
        assertPath(B, A, 1, 5);
        assertPath(B, F, 1, 8);
        assertPath(B, D, 2, 5);
        assertPath(C, F, 4, 14);
        assertThrows(IllegalArgumentException.class, () -> G.path(A));
        assertThrows(IllegalArgumentException.class, () -> A.path(B));
        assertThrows(IllegalArgumentException.class, () -> B.path(G));
    }

    @Test
    void paths() {
        assertEquals(1, A.paths(A).size());
        assertEquals(1, B.paths(A).size());
        assertEquals(1, B.paths(F).size());
        assertEquals(2, B.paths(D).size());
        assertEquals(3, C.paths(F).size());
        assertEquals(0, G.paths(B).size());
        assertEquals(0, B.paths(G).size());
        assertEquals(0, A.paths(B).size());
    }

    private void assertPath(Node source, Node destination, long expectedHopCount, long expectedCost) {
        Path p = source.path(destination);
        assertEquals(expectedHopCount, p.hopCount());
        assertEquals(expectedCost, p.cost(), EPSILON);
    }
}
/*
    Function        path()  paths()
    Classes         6 + 2   4 + 2
    Methods         25      21
    Lines of Code   40      37
    Avg method      1.6     1.8
    Test loc        47      55
    Test ratio      54%     60%
 */
