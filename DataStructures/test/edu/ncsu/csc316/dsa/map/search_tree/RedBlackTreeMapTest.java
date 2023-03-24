package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class RedBlackTreeMapTest {

	/**
	 * The BinarySearchTreeMap to be used for testing purposes
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1,  "one");
        assertEquals("one", tree.get(1));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        tree.put(2,  "two");
        tree.put(3,  "three");
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(4,  "four");
        tree.put(5,  "five");
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        assertEquals("four", tree.get(4));
        assertEquals("five", tree.get(5));
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases

        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1,  "one");
        tree.put(2,  "two");
        tree.put(3,  "three");
        tree.put(4,  "four");
        
        assertThrows(IllegalArgumentException.class, () -> tree.remove(tree.root()).getValue());
        assertEquals("three", tree.remove(3));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(6, "six");
        assertEquals("one", tree.remove(1));
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(5, "five");
        tree.put(8, "eight");
        tree.put(9, "nine");
        assertEquals("two", tree.remove(2));
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(8, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(9, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        assertEquals("four", tree.get(4));
        assertEquals("five", tree.get(5));
        assertEquals("six", tree.get(6));
        assertEquals("eight", tree.get(8));
        assertEquals("nine", tree.get(9));
        
        assertNull(tree.get(2));
        assertNull(tree.get(1));
        assertNull(tree.get(3));
        
        
        
      //  assertNull(tree.right(tree.root()).getElement());
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
}