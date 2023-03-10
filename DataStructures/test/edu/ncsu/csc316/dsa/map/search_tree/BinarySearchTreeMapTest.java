package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class BinarySearchTreeMapTest {
	/**
	 * The BinarySearchTreeMap to be used for testing purposes
	 */
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
        
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        
        Iterator<Integer> it = tree.iterator();
        assertEquals((Integer)5, it.next());
//        assertEquals((Integer)2, it.next());
//        assertEquals((Integer)3, it.next());
//        assertEquals((Integer)5, it.next());
//        assertEquals((Integer)4, it.next());
//        assertEquals("BalanceableBinaryTree[\n"
//        		+ "edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@2cb4893b\n"
//        		+ " edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@cc43f62\n"
//        		+ "  edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@5b218417\n"
//        		+ "   edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@645aa696\n"
//        		+ "    edu.ncsu.csc316.dsa.map.AbstractMap$MapEntry@6caf0677\n"
//        		+ "]", tree.toString());
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
    
    /**
     * Tests Students as keys in the map
     */
    @Test
    public void testStudent() {
    	Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        BinarySearchTreeMap<Student, String> sTree = new BinarySearchTreeMap<Student, String>();
        sTree.put(s1, "One");
        sTree.put(s2, "two");
        sTree.put(s3, "three");
        sTree.put(s4, "four");
        sTree.put(s5, "five");
        
        assertEquals(s1, sTree.root().getElement().getKey());
        assertEquals(s2, sTree.right(sTree.root()).getElement().getKey());
        assertEquals(s3, sTree.left(sTree.root()).getElement().getKey());
        assertEquals(s4, sTree.right(sTree.left(sTree.root())).getElement().getKey());
        assertEquals(s5, sTree.left(sTree.left(sTree.root())).getElement().getKey());
        
        
    }
}