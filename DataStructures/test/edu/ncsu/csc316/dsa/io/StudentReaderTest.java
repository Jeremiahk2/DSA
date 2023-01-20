package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * test class for StudentReader
 * @author Jeremiah Knizley
 *
 */
public class StudentReaderTest {
	
	/**
	 * Tests readInputAsArray to ensure all students are read and are in the correct place in the array
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * Tests that processLine puts information in the correct field in Student
	 */
	@Test
	public void testProcessLine() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");

			assertEquals("Amber", contents[0].getFirst());
			assertEquals("Michael", contents[0].getLast());
			assertEquals("michaea", contents[0].getUnityID());
			assertEquals(1, contents[0].getId());
			assertEquals(1.1, contents[0].getGpa(), .001);
			assertEquals(10, contents[0].getCreditHours());
			
			assertEquals("Ara", contents[1].getFirst());
			assertEquals("Marsh", contents[1].getLast());
			assertEquals("marsha", contents[1].getUnityID());
			assertEquals(3, contents[1].getId());
			assertEquals(2.25, contents[1].getGpa(), .001);
			assertEquals(11, contents[1].getCreditHours());
			
			assertEquals("Lacie", contents[2].getFirst());
			assertEquals("Mott", contents[2].getLast());
			assertEquals("mottl", contents[2].getUnityID());
			assertEquals(4, contents[2].getId());
			assertEquals(2.94, contents[2].getGpa(), .001);
			assertEquals(18, contents[2].getCreditHours());
	}
}
