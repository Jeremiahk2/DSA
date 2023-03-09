package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class UnorderedLinkedMapTest {

	/** map to be used for testing purposes */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertEquals("string3", map.put(3, "DifferentString"));
        assertNull(map.put(4, "AnotherDifferentString"));
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("string3", map.get(3));
        assertNull(map.put(5, "string5"));
        assertEquals("string5", map.get(5));
        assertNull(map.put(2, "string2"));
        assertEquals("string2", map.get(2));
        assertNull(map.put(4, "string4"));
        assertEquals("string4", map.get(4));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        map.get(2);
        
        assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
        
        map.get(80);
        assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5]", map.toString());
        
        assertEquals(null, map.remove(20));
        
        assertEquals("string4", map.remove(4));
        
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals("string1", map.get(it.next()));
        assertEquals("string4", map.get(it.next()));
        assertEquals("string2", map.get(it.next()));
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        assertEquals("string5", map.get(it.next()));
        assertEquals("string3", map.get(it.next()));
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(5, "string5"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(1, "string1"));
        Iterable<Entry<Integer, String>> iterable = map.entrySet();
        Iterator<Entry<Integer, String>> it = iterable.iterator();
        assertTrue(it.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals("string1", it.next().getValue());
        assertEquals( "string2", it.next().getValue());
        assertEquals( "string3", it.next().getValue());
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        assertEquals("string4", it.next().getValue());
        assertEquals( "string5", it.next().getValue());
        assertFalse(it.hasNext());
        
        
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<String> iterable = map.values();
        Iterator<String> it = iterable.iterator();
        
        assertTrue(it.hasNext());
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        
        assertEquals("string1", it.next());
        assertEquals( "string4", it.next());
        assertEquals( "string2", it.next());
        assertThrows(UnsupportedOperationException.class, () -> it.remove());
        assertEquals("string5", it.next());
        assertEquals( "string3", it.next());
        assertFalse(it.hasNext());
        
        
    }
}