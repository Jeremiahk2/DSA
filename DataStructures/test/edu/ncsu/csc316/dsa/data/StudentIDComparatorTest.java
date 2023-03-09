package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Test class for StudentIdComparator
 * @author Jeremiah Knizley
 *
 */
public class StudentIDComparatorTest {

	/** Test student one */
	private Student sOne;
	/** Test student two */
	private Student sTwo;
	/** Test student three */
	private Student sThree;
	/** Test student four */
	private Student sFour;
	/** Test student five */
	private Student sFive;
	/** The StudentIdComparator to be used for testing */
	private StudentIDComparator comparator;

	/**
	 * Setup method for testing purpose. Initializes fields.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Test method for Compare method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);

		assertEquals(comparator.compare(sThree, sThree), 0);
		
		assertTrue(comparator.compare(sFour, sFive) < 0);
		
		assertTrue(comparator.compare(sThree,  sFour) < 0);
	}


}
