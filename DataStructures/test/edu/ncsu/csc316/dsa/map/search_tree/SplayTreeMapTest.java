package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class SplayTreeMapTest {

	/**
	 * The BinarySearchTreeMap to be used for testing purposes
	 */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testSplay() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1,  "one");
        tree.put(2,  "two");
        tree.put(4,  "four");
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        
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
        
        assertEquals("three", tree.remove(3));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());

        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
//   
//    @Test
//    public void testStuff() {
//    	
//    }
    
//    /**
//     * Test the output of the remove(k) behavior
//     */     
//    @Test
//    public void testRemove() {
// 
//        // You should create test cases to check all the
//        // splay scenarios. The textbook has examples
//        // that you can use to create your test cases
//        
//        // You should check the specific keys in each node after adding or
//        // removing from the tree. For example, you might use:
//        //  assertEquals(4, (int)tree.root().getElement().getKey());
//        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
//    }
}