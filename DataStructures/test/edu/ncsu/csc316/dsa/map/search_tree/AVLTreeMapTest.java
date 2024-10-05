package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class AVLTreeMapTest {

	/**
	 * The BinarySearchTreeMap to be used for testing purposes
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1,  "one");
        tree.put(2,  "two");
        tree.put(4,  "four");
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("four", tree.get(4));
        
        tree.remove(1);
        tree.put(3, "three");
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("three", tree.get(3));
        assertEquals("two", tree.get(2));
        assertEquals("four", tree.get(4));
        
        assertNull(tree.get(1));
        
        
        
        
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
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
        tree.put(1,  "one");
        tree.put(2,  "two");
        tree.put(4,  "four");
        tree.put(10, "ten");
        tree.put(13,  "13");
        assertEquals("four", tree.remove(4));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(13, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertNull(tree.left(tree.right(tree.root())).getElement());
//        tree.put(8,  "eight");
//        assertEquals(8, (int)tree.left(tree.right(tree.root())).getElement().getKey());
//        tree.put(7,  "seven");
        
     
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
}