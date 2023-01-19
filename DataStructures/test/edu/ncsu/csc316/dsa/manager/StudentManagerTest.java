package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * test class for StudentManager
 * @author Jeremiah Knizley
 *
 */
public class StudentManagerTest {

	/**
	 * StudentManager object for testing purposes
	 */
	private StudentManager sm;
	
	/**
	 * sets up sm for testing purposes using the ascending ID input file
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
	}
	
	/**
	 * Tests the sorting method in StudentManager
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	// TODO: Add test cases
	// Suggestions:
	// -> Test that custom comparators sort the data correctly

}
