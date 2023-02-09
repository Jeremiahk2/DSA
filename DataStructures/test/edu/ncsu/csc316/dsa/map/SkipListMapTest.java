package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 *
 */
public class SkipListMapTest {

	/** integer/string map for testing purposes */
	private Map<Integer, String> map;
	/** Student/integer map for testing purposes */
	private Map<Student, Integer> studentMap;

	/**
	 * Create a new instance of a search table map before each test case executes
	 */     
	@Before
	public void setUp() {
		map = new SkipListMap<Integer, String>();
		studentMap = new SkipListMap<Student, Integer>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */     
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SkipListMap[3]", map.toString());
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
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		map.get(2);

		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

		map.get(80);
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals(null, map.get(6));
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
		assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());


		assertEquals("string3", map.remove(3));
		assertEquals("SkipListMap[1, 2, 4, 5]", map.toString());

		assertNull(map.remove(20));
		assertEquals("SkipListMap[1, 2, 4, 5]", map.toString());   
		assertEquals("string4", map.remove(4));
		assertEquals("SkipListMap[1, 2, 5]", map.toString());
	}

	/**
	 * Tests Map abstract data type behaviors to ensure the behaviors work
	 * as expected when using arbitrary objects as keys
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");

		assertTrue(studentMap.isEmpty());
		assertNull(studentMap.put(s1, 1));
		assertNull(studentMap.put(s2, 2));
		assertNull(studentMap.put(s3, 3));
		assertNull(studentMap.put(s4, 4));
		assertNull(studentMap.put(s5, 5));
		
		assertEquals("SkipListMap[Student [first=L, last=B, id=5, creditHours=0, gpa=0.0, unityID=lb], " +
   			 "Student [first=S, last=H, id=3, creditHours=0, gpa=0.0, unityID=sh], " +
   			 "Student [first=J, last=J, id=4, creditHours=0, gpa=0.0, unityID=jj], " +
   			 "Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk], " +
   			 "Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]", studentMap.toString());
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
		assertEquals("string2", map.get(it.next()));
		assertEquals("string3", map.get(it.next()));
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
		assertEquals("string4", map.get(it.next()));
		assertEquals("string5", map.get(it.next()));
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
		assertEquals( "string2", it.next());
		assertEquals( "string3", it.next());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
		assertEquals("string4", it.next());
		assertEquals( "string5", it.next());
		assertFalse(it.hasNext());
	}
}