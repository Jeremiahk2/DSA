package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * test class for RadixSorter
 * @author Jeremiah Knizley
 *
 */
public class RadixSorterTest {
	
	/** Testing Student one */
	private Student sOne;
	/** Testing Student two */
	private Student sTwo;
	/** Testing Student three */
	private Student sThree;
	/** Testing Student four */
	private Student sFour;
	/** Testing Student five */
	private Student sFive;
	/** CountingSorter for testing purposes */
	private RadixSorter<Student> sorter;

	/** Setup method. Initializes fields to testing values */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new RadixSorter<Student>();
	}

	/**
	 * Tests sorting when in random order
	 */
	@Test
	public void testSortRandom() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * Tests sorting students with reverse ID order
	 */
	@Test
	public void testSortReverse() {
		Student[] reverse = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(reverse);
		assertEquals(sOne, reverse[0]);
		assertEquals(sTwo, reverse[1]);
		assertEquals(sThree, reverse[2]);
		assertEquals(sFour, reverse[3]);
		assertEquals(sFive, reverse[4]);
	}
	
	/**
	 * Tests sorting students in the correct order already
	 */
	@Test
	public void testSortInOrder() {
		Student[] ascending = {sOne, sTwo, sThree, sFour, sFive};
		sorter.sort(ascending);
		assertEquals(sOne, ascending[0]);
		assertEquals(sTwo, ascending[1]);
		assertEquals(sThree, ascending[2]);
		assertEquals(sFour, ascending[3]);
		assertEquals(sFive, ascending[4]);
	}

}
