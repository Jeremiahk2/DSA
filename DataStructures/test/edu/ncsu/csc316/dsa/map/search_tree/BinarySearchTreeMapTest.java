package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 *
 */
public class BinarySearchTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(4, "four");
        assertEquals(2, tree.size());
        assertEquals("four", tree.get(4));
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        
        assertEquals(0, tree.size());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(2, tree.size());
        assertEquals("two", tree.get(2));
        
        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertEquals("three", tree.get(3));
        
        tree.put(5, "five");
        assertEquals(4, tree.size());
        assertEquals("five", tree.get(5));
        
        tree.put(4, "four");
        assertEquals(5, tree.size());
        assertEquals("four", tree.get(4));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(2, "two");
        tree.put(1,  "one");
        
        assertEquals("two",  tree.remove(2));
        assertNull(tree.remove(2));
        
        tree.put(3, "three");
        tree.put(0,  "zero");
        tree.put(4, "four");
        
        assertEquals("three", tree.remove(3));
        assertNull(tree.remove(3));
        
        assertEquals("one", tree.remove(1));
        assertEquals("four", tree.root().getElement().getValue());
        assertNull(tree.remove(1));
        assertEquals("four", tree.get(4));
        assertEquals("zero", tree.get(0));
        
        
        // You should create tests to ensure removing works
        // in all special cases:
        //   - removing the root
        //   - removing from a node that has only a left child
        //   - removing from a node that has only a right child
        //   - removing from a node that has both children
        // etc.
    }
}