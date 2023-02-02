package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 *
 */
public class ArrayBasedQueueTest {

	/** queue used for testing purposes */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        
        assertEquals(2, queue.size());
        
        queue.enqueue("three");
        
        assertEquals(3, queue.size());
        
        assertEquals("one", queue.dequeue());
        
        assertEquals(2, queue.size());
        
        assertEquals("two", queue.dequeue());
        
        assertEquals(1, queue.size());
        queue.enqueue("four");
//        assertEquals(4, queue.size());
//        assertEquals(5, queue.size());
//        assertEquals("one", queue.dequeue());
//        assertEquals(4, queue.size());
//        assertEquals("two", queue.dequeue());
//        assertEquals(3, queue.size());
        assertEquals("three", queue.dequeue());
        assertEquals(1, queue.size());
        //enqueue well past capacity to test wrapping around
        queue.enqueue("six");
        assertEquals(2, queue.size());
        queue.enqueue("seven");
        assertEquals(3, queue.size());
        queue.enqueue("eight");
        assertEquals(4, queue.size());
        queue.enqueue("nine");
        assertEquals(5, queue.size());
        queue.enqueue("ten");
        assertEquals(6, queue.size());
        queue.enqueue("eleven");
        assertEquals(7, queue.size());
        queue.enqueue("twelve");
        assertEquals(8, queue.size());
        queue.enqueue("thirteen");
        assertEquals(9, queue.size());
        assertEquals("four", queue.front());
        assertEquals("four", queue.dequeue());
        queue.enqueue("fourteen");
//        assertEquals("five", queue.dequeue());
        assertEquals("six", queue.dequeue());
        assertEquals("seven", queue.dequeue());
        assertEquals("eight", queue.dequeue());
        assertEquals("nine", queue.dequeue());
        assertEquals("ten", queue.dequeue());
        assertEquals("eleven", queue.dequeue());
        assertEquals("twelve", queue.dequeue());
        assertEquals("thirteen", queue.dequeue());
        assertEquals("fourteen", queue.dequeue());
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        queue.enqueue("one");
        assertEquals("one", queue.dequeue());
        queue.enqueue("two");
        queue.enqueue("three");
        assertEquals("two", queue.dequeue());
        queue.enqueue("four");
        queue.enqueue("five");
        assertEquals("three", queue.dequeue());
        assertEquals("four", queue.dequeue());
        assertEquals("five", queue.dequeue());
        queue.enqueue("six");
        assertEquals("six", queue.dequeue());
    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
    	queue = new ArrayBasedQueue<String>(5);
    	assertEquals(0, queue.size());
    	assertThrows(NoSuchElementException.class, () -> queue.front());
    	
    	queue.enqueue("one");
    	assertEquals("one", queue.front());
    	queue.enqueue("two");
    	assertEquals("one", queue.front());
    	queue.enqueue("three");
    	assertEquals("one", queue.front());
    	queue.enqueue("four");
    	assertEquals("one", queue.front());
    	queue.enqueue("five");
    	assertEquals("one", queue.front());
    	
    	queue.dequeue();
    	assertEquals("two", queue.front());
    	queue.dequeue();
    	assertEquals("three", queue.front());
    	queue.dequeue();
    	assertEquals("four", queue.front());
    	
    	queue.enqueue("six");
    	assertEquals("four", queue.front());
    	queue.enqueue("seven");
    	assertEquals("four", queue.front());
    	queue.enqueue("eight");
    	assertEquals("four", queue.front());
    	
    	queue.dequeue();
    	assertEquals("five", queue.front());
    	queue.dequeue();
    	assertEquals("six", queue.front());
    	queue.dequeue();
    	assertEquals("seven", queue.front());
    	queue.dequeue();
    	assertEquals("eight", queue.front());
    	queue.dequeue();
    	assertThrows(NoSuchElementException.class, () -> queue.front());
    	
    	queue.enqueue("newOne");
    	assertEquals("newOne", queue.front());
    	queue.enqueue("newTwo");
    	assertEquals("newOne", queue.front());
    	queue.enqueue("newThree");
    	assertEquals("newOne", queue.front());
    	queue.enqueue("newFour");
    	assertEquals("newOne", queue.front());
    	queue.enqueue("newFive");
    	assertEquals("newOne", queue.front());
    	
    	queue.dequeue();
    	assertEquals("newTwo", queue.front());
    	queue.dequeue();
    	assertEquals("newThree", queue.front());
    	queue.dequeue();
    	assertEquals("newFour", queue.front());
    	queue.dequeue();
    	assertEquals("newFive", queue.front());
    	
    	queue.enqueue("six");
    	assertEquals("newFive", queue.front());
    	queue.enqueue("seven");
    	assertEquals("newFive", queue.front());
    	queue.enqueue("eight");
    	assertEquals("newFive", queue.front());
    	queue.enqueue("nine");
    	assertEquals("newFive", queue.front());
    	
    	queue.enqueue("ten");
    	assertEquals("newFive", queue.front());
    	assertEquals("newFive", queue.dequeue());
    	assertEquals("six", queue.front());
    }

}