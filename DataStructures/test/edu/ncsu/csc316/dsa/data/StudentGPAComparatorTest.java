package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for StudentGPAComparator
 * @author Jeremiah Knizley
 *
 */
public class StudentGPAComparatorTest {

	/** Test Student one */
	private Student sOne;
	/** Test Student two */
	private Student sTwo;
	/** Test Student three */
	private Student sThree;
	/** Test Student Four */
	private Student sFour;
	/** Test Student five */
	private Student sFive;
	/** Comparator object for testing purposes */
	private StudentGPAComparator comparator;

	/**
	 * setup method for testing purposes. Initializes fields.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 2.000001, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * Test method for Compare method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);

		assertEquals(comparator.compare(sOne, sOne), 0);
		assertTrue(comparator.compare(sFour, sFive) > 0);
		assertFalse(comparator.compare(sFive, sFour) > 0);
		
		assertTrue(comparator.compare(sThree, sTwo) < 0);
	}

}
