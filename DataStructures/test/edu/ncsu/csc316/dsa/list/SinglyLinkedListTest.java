package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 *
 */
public class SinglyLinkedListTest {

	/** list used for testing purposes */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
        list.addLast("First");
        assertEquals(1, list.size());
        assertEquals("First", list.get(0));
        list.addLast("Second");
        assertEquals(2, list.size());
        assertEquals("Second", list.get(1));
        
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.last());
    	
    	list.addLast("First");
    	assertEquals("First", list.last());
    	list.addLast("Second");
    	assertEquals("Second", list.last());
    	list.addLast("Third");
    	assertEquals("Third", list.last());
    	list.addLast("Fourth");
    	assertEquals("Fourth", list.last());
    	list.addLast("Fifth");
    	assertEquals("Fifth", list.last());
    	list.addFirst("NewFirst");
    	assertEquals("Fifth", list.last());
    	
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	list.addFirst("First");
    	assertEquals("First", list.get(0));
    	assertEquals(1, list.size());
    	list.addFirst("Second");
    	assertEquals(2, list.size());
    	assertEquals("First", list.get(1));
    	assertEquals("Second", list.get(0));
    	list.addFirst("Third");
    	assertEquals(3, list.size());
    	assertEquals("First", list.get(2));
    	assertEquals("Second", list.get(1));
    	assertEquals("Third", list.get(0));
    	list.addFirst("Fourth");
    	assertEquals(4, list.size());
    	assertEquals("First", list.get(3));
    	assertEquals("Second", list.get(2));
    	assertEquals("Third", list.get(1));
    	assertEquals("Fourth", list.get(0));
    	list.addFirst("Fifth");
    	assertEquals(5, list.size());
    	assertEquals("First", list.get(4));
    	assertEquals("Second", list.get(3));
    	assertEquals("Third", list.get(2));
    	assertEquals("Fourth", list.get(1));
    	assertEquals("Fifth", list.get(0));
    	
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	//assertThrows(IndexOutOfBoundsException.class, () -> list.first());
    	list.addFirst("First");
    	assertEquals("First", list.first());
    	list.addFirst("Second");
    	assertEquals("Second", list.first());
    	list.addFirst("Third");
    	assertEquals("Third", list.first());
    	list.addFirst("Fourth");
    	assertEquals("Fourth", list.first());
    	list.addFirst("Fifth");
    	assertEquals("Fifth", list.first());
    	list.addLast("Sixth");
    	assertEquals("Fifth", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An UnsupportedOperationException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

//        it.remove();
//        assertEquals(0, list.size());
//        assertTrue(list.isEmpty());
//        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
//        try{
//            it.remove();
//            fail("An IllegalStateException should have been thrown");           
//        } catch(Exception e) {
//            assertTrue(e instanceof IllegalStateException);
//        }
        
        list.addLast("Two");
        list.addLast("Three");
        list.addLast("Four");
        assertEquals("Two", it.next());
        assertEquals("Three", it.next());
        assertEquals("Four", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    	
    	list.addLast("First");
    	list.addLast("Second");
    	list.addLast("Third");
    	list.addLast("Fourth");
    	list.addLast("Fifth");
    	list.addLast("Sixth");
    	assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    	assertThrows(IndexOutOfBoundsException.class, () -> list.remove(6));
    	
    	assertEquals("Fourth", list.remove(3));
    	assertEquals("Fifth", list.get(3));
    	assertEquals(5, list.size());
    	assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    	assertEquals("First", list.remove(0));
    	assertEquals(4, list.size());
    	assertEquals("Second", list.get(0));
    	
    	
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.removeFirst());
    	
    	list.addLast("First");
    	list.addLast("Second");
    	list.addLast("Third");
    	list.addLast("Fourth");
    	list.addLast("Fifth");
    	list.addLast("Sixth");
    	
    	assertEquals("First", list.removeFirst());
    	assertEquals(5, list.size());
    	assertEquals("Second", list.removeFirst());
    	assertEquals(4, list.size());
    	assertEquals("Third", list.removeFirst());
    	assertEquals(3, list.size());
    	assertEquals("Fourth", list.removeFirst());
    	assertEquals(2, list.size());
    	assertEquals("Fifth", list.removeFirst());
    	assertEquals(1, list.size());
    	
    	list.addLast("Extra");
    	assertEquals("Sixth", list.removeFirst());
    	assertEquals(1, list.size());
    	list.addFirst("ExtraExtra");
    	assertEquals("ExtraExtra", list.removeFirst());
    	assertEquals(1, list.size());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.removeLast());
    	
    	list.addLast("First");
    	list.addLast("Second");
    	list.addLast("Third");
    	list.addLast("Fourth");
    	list.addLast("Fifth");
    	list.addLast("Sixth");
    	
    	assertEquals("Sixth", list.removeLast());
    	assertEquals("Fifth", list.removeLast());
    	assertEquals("Fourth", list.removeLast());
    	assertEquals("Third", list.removeLast());
    	assertEquals("Second", list.removeLast());
    	list.addLast("Extra");
    	assertEquals("Extra", list.removeLast());
    	list.addFirst("ExtraExtra");
    	assertEquals("First", list.removeLast());
    	
    	
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "FailedFirst"));
    	
    	list.addLast("First");
    	list.addLast("Second");
    	list.addLast("Third");
    	list.addLast("Fourth");
    	list.addLast("Fifth");
    	list.addLast("Sixth");
    	
    	assertThrows(IndexOutOfBoundsException.class, () -> list.set(6, "FailedFirst"));
    	
    	list.set(3, "BetterFourth");
    	
    	assertEquals("BetterFourth", list.get(3));
    	assertThrows(IndexOutOfBoundsException.class, () -> list.set(6, "FailedFirst"));
    	
    	list.set(0, "MuchBetterFirst");
    	
    	assertEquals("MuchBetterFirst", list.get(0));
    	
    	list.set(5, "MediocreSixth");
    	
    	assertEquals("MediocreSixth", list.get(5));
    }
}