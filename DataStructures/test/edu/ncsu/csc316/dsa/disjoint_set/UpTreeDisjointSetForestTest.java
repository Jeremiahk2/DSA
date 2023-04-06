package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class UpTreeDisjointSetForestTest {

	/**
	 * DisjointSetForest used for testing purposes
	 */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        assertEquals(one, set.find("one"));
        assertEquals(two, set.find("two"));
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> a = set.makeSet("A");
        Position<String> b = set.makeSet("B");
        Position<String> c = set.makeSet("C");
        Position<String> d = set.makeSet("D");
        Position<String> e = set.makeSet("E");
        Position<String> f = set.makeSet("F");
        Position<String> g = set.makeSet("G");
        Position<String> h = set.makeSet("H");
        Position<String> i = set.makeSet("I");
        Position<String> j = set.makeSet("J");
        Position<String> k = set.makeSet("K");
        Position<String> l = set.makeSet("L");
        assertEquals(a, set.find("A"));
        assertEquals(b, set.find("B"));
        assertEquals(c, set.find("C"));
        assertEquals(d, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(f, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(h, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(j, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(l, set.find("L"));
        //Example from textbook.
        set.union(a, c);
        assertEquals(c, set.find("A"));
        assertEquals(b, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(d, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(f, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(h, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(j, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(l, set.find("L"));
        set.union(b, d);
        assertEquals(c, set.find("A"));
        assertEquals(d, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(d, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(f, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(h, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(j, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(l, set.find("L"));
        set.union(f, i);
        assertEquals(c, set.find("A"));
        assertEquals(d, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(d, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(i, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(h, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(j, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(l, set.find("L"));
        set.union(l, k);
        assertEquals(c, set.find("A"));
        assertEquals(d, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(d, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(i, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(h, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(j, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(k, set.find("L"));
        set.union(j, k);
        assertEquals(c, set.find("A"));
        assertEquals(d, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(d, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(i, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(h, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(k, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(k, set.find("L"));
        set.union(h, k);
        assertEquals(c, set.find("A"));
        assertEquals(d, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(d, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(i, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(k, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(k, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(k, set.find("L"));
        set.union(d, k);
        assertEquals(c, set.find("A"));
        assertEquals(k, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(k, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(i, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(k, set.find("H"));
        assertEquals(i, set.find("I"));
        assertEquals(k, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(k, set.find("L"));
        set.union(k, i);
        assertEquals(c, set.find("A"));
        assertEquals(k, set.find("B"));
        assertEquals(c, set.find("C"));
        
        assertEquals(k, set.find("D"));
        assertEquals(e, set.find("E"));
        assertEquals(k, set.find("F"));
        assertEquals(g, set.find("G"));
        assertEquals(k, set.find("H"));
        assertEquals(k, set.find("I"));
        assertEquals(k, set.find("J"));
        assertEquals(k, set.find("K"));
        assertEquals(k, set.find("L"));
        
        
    }
}