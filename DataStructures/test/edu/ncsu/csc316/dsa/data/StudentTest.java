package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Student
 * @author Jeremiah Knizley
 *
 */
public class StudentTest {

	/** First Student object for testing purposes */
	private Student sOne;
	/** Second Student object for testing purposes */
	private Student sTwo;
//	/** Third Student object for testing purposes */
//	private Student sThree;
//	/** Fourth Student object for testing purposes */
//	private Student sFour;
//	/** Fifth Student object for testing purposes */
//	private Student sFive;

	/**
	 * Sets up the five student objects for testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
//		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
//		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
//		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Test method for setFirst()
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Test method for setLast()
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * test method for setId()
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * test method for setGpa()
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * test method for setUnityID()
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * test method for compareTo()
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		int sOneCompareValue = sOne.compareTo(sOne);
		assertEquals(sOneCompareValue, 0);
	}
	/**
	 * tests equals method
	 */
	@Test
	public void testEquals() {
		Student duplicate = new Student("OneFirst", "OneLast", 1, 1, 2.0, "DuplicateUnityID");
		assertTrue(duplicate.equals(sOne));
		assertTrue(sOne.equals(sOne));
		
		Student sameFirst = new Student("OneFirst", "NotOneLast", 1, 1, 1.0, "DuplicateUnityID");
		assertFalse(sameFirst.equals(sOne));
	}
	/**
	 * Tests toString
	 */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(), "Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]");
	}
	/**
	 * Tests hashCode
	 */
	@Test
	public void testHashCode() {
		Student duplicate = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		assertEquals(duplicate.hashCode(), sOne.hashCode());
	}
}
