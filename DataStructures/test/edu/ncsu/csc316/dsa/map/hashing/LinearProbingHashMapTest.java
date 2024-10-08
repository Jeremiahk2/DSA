package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 *
 */
public class LinearProbingHashMapTest {
	
	/**
	 * Map used for testing purposes
	 */
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
    	assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        map.put(11, "string11");
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey()); //4 comes first because of the inOrder traversal of the AVLTreeMap
        assertEquals(11, (int)it.next().getKey());
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string11", map.get(11));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
    	assertTrue(map.isEmpty());
        assertNull(map.put(4, "string4")); //Same hash as 11
        assertNull(map.put(3, "string3"));
        assertNull(map.put(11, "fake11")); //Same hash as 4
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        assertEquals("fake11", map.put(11, "string11"));
        assertEquals(3, map.size());
        
        assertEquals("string11", map.remove(11));
        assertEquals(2, map.size());
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertThrows(NoSuchElementException.class, () -> it.next());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertNull(map.put(4, "string4")); //Same hash as 11
        assertNull(map.put(3, "string3"));
        assertNull(map.put(11, "string11")); //Same hash as 4
        
        Iterator<Integer> it = map.iterator();
        assertEquals("string3", map.get(it.next()));
        assertEquals("string4", map.get(it.next()));
        assertEquals("string11", map.get(it.next()));
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	assertNull(map.put(4, "string4")); //Same hash as 11
        assertNull(map.put(3, "string3"));
        assertNull(map.put(11, "string11")); //Same hash as 4
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        assertEquals("string3", it.next().getValue());
        assertEquals("string4", it.next().getValue());
        assertEquals("string11", it.next().getValue());
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertNull(map.put(4, "string4")); //Same hash as 11
        assertNull(map.put(3, "string3"));
        assertNull(map.put(11, "string11")); //Same hash as 4
        
        Iterator<String> it = map.values().iterator();
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertEquals("string11", it.next());
    }
    /**
     * Test to make sure all constructors work. Not all are needed for testing though.
     */
    @Test
    public void testConstructors() {
    	map = new LinearProbingHashMap<Integer, String>(true);
    	//Try adding 18 courses (past capacity)
    	assertNull(map.put(1, "string"));
    	assertNull(map.put(2, "string"));
    	assertNull(map.put(3, "string"));
    	assertNull(map.put(4, "string"));
    	assertNull(map.put(5, "string"));
    	assertNull(map.put(6, "string"));
    	assertNull(map.put(7, "string"));
    	assertNull(map.put(8, "string"));
    	assertNull(map.put(9, "string"));
    	assertNull(map.put(10, "string"));
    	assertNull(map.put(11, "string"));
    	assertNull(map.put(12, "string"));
    	assertNull(map.put(13, "string"));
    	assertNull(map.put(14, "string"));
    	assertNull(map.put(15, "string"));
    	assertNull(map.put(16, "string"));
    	assertNull(map.put(17, "string"));
    	assertNull(map.put(18, "string"));
    	
    	map = new LinearProbingHashMap<Integer, String>(3);
    	assertNull(map.put(1, "string"));
    	assertNull(map.put(2, "string"));
    	assertNull(map.put(3, "string"));
    	assertNull(map.put(4, "string"));
    	
    	map = new LinearProbingHashMap<Integer, String>();
    	assertNull(map.put(1, "string"));
    	assertNull(map.put(2, "string"));
    	assertNull(map.put(3, "string"));
    	assertNull(map.put(4, "string"));
    	assertNull(map.put(5, "string"));
    	assertNull(map.put(6, "string"));
    	assertNull(map.put(7, "string"));
    	assertNull(map.put(8, "string"));
    	assertNull(map.put(9, "string"));
    	assertNull(map.put(10, "string"));
    	assertNull(map.put(11, "string"));
    	assertNull(map.put(12, "string"));
    	assertNull(map.put(13, "string"));
    	assertNull(map.put(14, "string"));
    	assertNull(map.put(15, "string"));
    	assertNull(map.put(16, "string"));
    	assertNull(map.put(17, "string"));
    	assertNull(map.put(18, "string"));
    	
    }
}