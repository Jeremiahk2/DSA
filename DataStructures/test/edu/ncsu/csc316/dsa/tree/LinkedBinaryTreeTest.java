package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class LinkedBinaryTreeTest {

	/** Tree used for testing purposes */
    private LinkedBinaryTree<String> tree;
    /** First position in test tree */
    private Position<String> one;
    /** Second position in test tree */
    private Position<String> two;
    /** Third position in test tree */
    private Position<String> three;
    /** Fourth position in test tree */
    private Position<String> four;
    /** Fifth position in test tree */
    private Position<String> five;
    /** Sixth position in test tree */
    private Position<String> six;
    /** Seventh position in test tree */
    private Position<String> seven;
    /** Eighth position in test tree */
    private Position<String> eight;
    /** Ninth position in test tree */
    private Position<String> nine;
    /** Tenth position in test tree */
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        tree.set(one, "newOne");
        assertEquals(tree.root().getElement(), "newOne");
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " three\n"
        		+ "  four\n"
        		+ "   eight\n"
        		+ "   nine\n"
        		+ "]", tree.toString());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        assertEquals(2, tree.numChildren(ten));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals(null, tree.parent(one));
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        assertEquals(three, tree.parent(four));
        assertEquals(ten, tree.parent(five));
        assertEquals(two, tree.parent(six));
        assertEquals(ten, tree.parent(seven));
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
        assertEquals(two, tree.parent(ten));
        
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(null, tree.sibling(one));
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        assertEquals(null, tree.sibling(four));
        assertEquals(seven, tree.sibling(five));
        assertEquals(ten, tree.sibling(six));
        assertEquals(five, tree.sibling(seven));
        assertEquals(nine, tree.sibling(eight));
        assertEquals(eight, tree.sibling(nine));
        assertEquals(six, tree.sibling(ten));
        
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(four));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
        assertTrue(tree.isInternal(ten));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(four));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
        assertFalse(tree.isLeaf(ten));
        
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        assertEquals("one", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("six", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("three", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        // the coding activity task for implementing traversals
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        assertEquals("six", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("three", it.next().getElement());
        assertEquals("one", it.next().getElement());
        // the coding activity task for implementing traversals
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals("six", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("one", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        assertEquals("three", it.next().getElement());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertNull(tree.root());
    }
    
    /** 
     * Tests the output of the LevelOrder traversal behavior 
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals("one", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("three", it.next().getElement());
        assertEquals("six", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        // the coding activity task for implementing traversals
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	createTree();
        assertThrows(IllegalArgumentException.class, () -> tree.addLeft(four, "Eleven"));
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	createTree();
        assertThrows(IllegalArgumentException.class, () -> tree.addRight(four, "Eleven"));
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
//        assertThrows(IllegalArgumentException.class, () -> tree.remove(four));
        assertEquals("nine", tree.remove(nine));
        assertEquals("three", tree.remove(three));
        assertEquals("four", tree.remove(four));
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " eight\n"
        		+ "]", tree.toString());
        assertEquals("six", tree.remove(six));
        assertEquals("two", tree.remove(two));
        
    }
}