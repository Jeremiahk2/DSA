package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class PositionalLinkedListTest {

	/** a PositionalList that contains strings to use for testing */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("second");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        
        list.addLast("Last");
        assertEquals(3, list.size());
        assertEquals(second, list.first());
        
        
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        list.addFirst("second");
        assertEquals(2, list.size());
        assertEquals(first, list.last());
        
        Position<String> last = list.addLast("Last");
        assertEquals(3, list.size());
        assertEquals(last, list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("Second");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        assertEquals(first, list.last());
        assertEquals(first, list.after(second));
        
        Position<String> newFirst = list.addFirst("NewFirst");
        
        assertEquals(second, list.after(newFirst));
        assertEquals(newFirst, list.before(second));
        
        assertNull(list.before(newFirst));
        
        
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        assertNull(list.after(first));
        assertNull(list.before(first));
        
        Position<String> second = list.addLast("Second");
        assertEquals(second, list.last());
        assertNull(list.after(second));
        assertEquals(first, list.before(second));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        assertNull(list.after(first));
        assertNull(list.before(first));
        
        Position<String> second = list.addBefore(first, "Second");
        assertEquals(second, list.before(first));
        assertEquals(first, list.after(second));
        
        Position<String> third = list.addBefore(first, "third");
        assertEquals(second, list.first());
        Position<String> fourth = list.addBefore(third, "fourth");
        assertEquals(third, list.after(fourth));
        assertEquals(fourth, list.before(third));
        Position<String> fifth = list.addBefore(first, "fifth");
        assertEquals(first, list.last());
        assertEquals(third, list.before(fifth));
        assertEquals(fifth, list.after(third));
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	Position<String> first = list.addFirst("one");
    	Position<String> second = list.addAfter(first, "Second");
    	assertEquals(second, list.after(first));
    	assertEquals(first, list.before(second));
    	assertEquals(second, list.last());
    	Position<String> third = list.addAfter(second, "third");
    	assertEquals(third, list.after(second));
    	assertEquals(second, list.before(third));
    	assertEquals(third, list.last());
    	Position<String> fourth = list.addAfter(third, "fourth");
    	assertEquals(fourth, list.after(third));
    	assertEquals(third, list.before(fourth));
    	assertEquals(fourth, list.last());
    	Position<String> fifth = list.addAfter(fourth, "fifth");
    	assertEquals(fifth, list.after(fourth));
    	assertEquals(fourth, list.before(fifth));
    	assertEquals(fifth, list.last());
    	
    	assertEquals(first, list.first());
    	
    	Position<String> wildcard = list.addAfter(second, "wildcard");
    	
    	assertEquals(wildcard, list.after(second));
    	assertEquals(second, list.before(wildcard));
    	assertEquals(6, list.size());
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	Position<String> first = list.addFirst("one");
    	Position<String> second = list.addAfter(first, "Second");
    	Position<String> third = list.addAfter(second, "third");
    	Position<String> fourth = list.addAfter(third, "fourth");
    	Position<String> fifth = list.addAfter(fourth, "fifth");
    	String oldFifth = list.set(fifth, "BetterFifth");
    	assertEquals(oldFifth, "fifth");
    	assertEquals("BetterFifth", fifth.getElement());
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> first = list.addFirst("first");
    	Position<String> second = list.addAfter(first, "second");
    	Position<String> third = list.addAfter(second, "third");
    	Position<String> fourth = list.addAfter(third, "fourth");
    	Position<String> fifth = list.addAfter(fourth, "fifth");
    	assertEquals("fifth", list.remove(fifth));
    	assertEquals(fourth, list.last());
    	assertNull(list.after(fourth));
    	assertEquals("first", list.remove(first));
    	assertEquals(second, list.first());
    	assertEquals("third", list.remove(third));
    	assertEquals(fourth, list.after(second));
    	assertEquals(second, list.before(fourth));
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
        //TODO: complete this test case
        // Use your ArrayBasedList and SinglyLinkedList test cases as a guide
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        //TODO: complete this test case
    }

}