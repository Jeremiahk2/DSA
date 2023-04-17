package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/**
	 * HeapAdaptablePriorityQueue for testing purposes
	 */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        /*Entry<Integer, String> e4 = */heap.insert(4, "four");
        /*Entry<Integer, String> e3 = */heap.insert(3, "three");
        /*Entry<Integer, String> e2 = */heap.insert(2, "two");
        /*Entry<Integer, String> e1 = */heap.insert(1, "one");
        /*Entry<Integer, String> e0 = */heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e7, -10);
        assertEquals(-10, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e6, -8);
        assertEquals(-10, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e5, -15);
        assertEquals(-15, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(9, heap.size());
        
        
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        /*Entry<Integer, String> e6 = */heap.insert(6, "six");
        /*Entry<Integer, String> e5 = */heap.insert(5, "five");
        /*Entry<Integer, String> e4 = */heap.insert(4, "four");
        /*Entry<Integer, String> e3 = */heap.insert(3, "three");
        /*Entry<Integer, String> e2 = */heap.insert(2, "two");
        /*Entry<Integer, String> e1 = */heap.insert(1, "one");
        /*Entry<Integer, String> e0 = */heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        
        
        heap.replaceValue(e7,  "SEVEN");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("SEVEN",  e7.getValue());
        
        
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        /*Entry<Integer, String> e8 = */heap.insert(8, "eight");
        /*Entry<Integer, String> e7 = */heap.insert(7, "seven");
        /*Entry<Integer, String> e6 = */heap.insert(6, "six");
        /*Entry<Integer, String> e5 = */heap.insert(5, "five");
        /*Entry<Integer, String> e4 = */heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        
        heap.remove(e1);
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(7, heap.size());
        
        heap.remove(e2);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(6, heap.size());
        
        heap.replaceValue(e3,  "THREE");
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("THREE", heap.min().getValue());
        assertEquals(6, heap.size());
        assertEquals("THREE",  e3.getValue());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        Entry<Student, String> es1 = sHeap.insert(s1, "one");
        Entry<Student, String> es2 = sHeap.insert(s2, "two");
        Entry<Student, String> es3 = sHeap.insert(s3, "three");
        Entry<Student, String> es4 = sHeap.insert(s4, "four");
        Entry<Student, String> es5 = sHeap.insert(s5, "five");

        sHeap.remove(es1);
        assertEquals(2, (int)sHeap.min().getKey().getId());
        assertEquals("two", sHeap.min().getValue());
        assertEquals(4, sHeap.size());
        
        sHeap.remove(es2);
        assertEquals(3, (int)sHeap.min().getKey().getId());
        assertEquals("three", sHeap.min().getValue());
        assertEquals(3, sHeap.size());
        
        sHeap.remove(es3);
        assertEquals(4, (int)sHeap.min().getKey().getId());
        assertEquals("four", sHeap.min().getValue());
        assertEquals(2, sHeap.size());
        
        sHeap.replaceValue(es4,  "FOUR");
        assertEquals(4, (int)sHeap.min().getKey().getId());
        assertEquals("FOUR", sHeap.min().getValue());
        assertEquals(2, sHeap.size());
        assertEquals("FOUR",  es4.getValue());
        
        sHeap.replaceKey(es5, s1);
        
        assertEquals(1, (int)sHeap.min().getKey().getId());
        assertEquals("five", sHeap.min().getValue());
        assertEquals(2, sHeap.size());
        assertEquals("five",  es5.getValue());
    }
}