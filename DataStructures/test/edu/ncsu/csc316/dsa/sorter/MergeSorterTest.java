package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the InsertionSort algorithm in InsertionSorter
 * @author Jeremiah Knizley
 *
 */
public class MergeSorterTest {

	/** Test data in ascending (correct) order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Test data in descending (opposite) order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** Test data in a random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** test data with large gaps (also random) */
	private Integer[] largeGaps = {3, 30, 15, 100, 300, 50, 600};
	/** test data with negative numbers */
	private Integer[] negative = {-30, -80, -15, -68, -23, -14};
	/** test data with duplicate numbers */
	private Integer[] duplicates = {15, -30, -30, 28, 28, 45};
	/** InsertionSorter object for sorting integers */
	private MergeSorter<Integer> integerSorter;
	
	/** InsertionSorter object for sorting Students */
	private MergeSorter<Student> studentSorter;
	/** First Student object for testing purposes */
	private Student sOne;
	/** Second Student object for testing purposes */
	private Student sTwo;
	/** Third Student object for testing purposes */
	private Student sThree;
	/** Fourth Student object for testing purposes */
	private Student sFour;
	/** Fifth Student object for testing purposes */
	private Student sFive;
	/** A student with the same last name as sFive */
	private Student duplicateLastFive;
	/** a student with the same first and last name as sFive */
	private Student duplicateFive;
	/** Array of students for testing purposes */
	private Student[] students;

	/**
	 * sets up the InsertionSorter for new tests
	 */
	@Before
	public void setUp() {
		integerSorter = new MergeSorter<Integer>();
		studentSorter = new MergeSorter<Student>();
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		duplicateLastFive = new Student("DuplicateFirst", "FiveLast", 6, 6, 6.0, "sixUnityID");
		duplicateFive = new Student("FiveFirst", "FiveLast", 7, 7, 7.0, "sevenUnityId");
		
		
	}
	
	/**
	 * Tests sorting integers using the InsertionSorter
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals((Integer)1, dataAscending[0]);
		assertEquals((Integer)2, dataAscending[1]);
		assertEquals((Integer)3, dataAscending[2]);
		assertEquals((Integer)4, dataAscending[3]);
		assertEquals((Integer)5, dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals((Integer)1, dataDescending[0]);
		assertEquals((Integer)2, dataDescending[1]);
		assertEquals((Integer)3, dataDescending[2]);
		assertEquals((Integer)4, dataDescending[3]);
		assertEquals((Integer)5, dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals((Integer)1, dataRandom[0]);
		assertEquals((Integer)2, dataRandom[1]);
		assertEquals((Integer)3, dataRandom[2]);
		assertEquals((Integer)4, dataRandom[3]);
		assertEquals((Integer)5, dataRandom[4]);
		
		integerSorter.sort(largeGaps);
		assertEquals((Integer)3, largeGaps[0]);
		assertEquals((Integer)15, largeGaps[1]);
		assertEquals((Integer)30, largeGaps[2]);
		assertEquals((Integer)50, largeGaps[3]);
		assertEquals((Integer)100, largeGaps[4]);
		assertEquals((Integer)300, largeGaps[5]);
		assertEquals((Integer)600, largeGaps[6]);
		
		integerSorter.sort(negative);
		assertEquals((Integer)(-80), negative[0]);
		assertEquals((Integer)(-68), negative[1]);
		assertEquals((Integer)(-30), negative[2]);
		assertEquals((Integer)(-23), negative[3]);
		assertEquals((Integer)(-15), negative[4]);
		assertEquals((Integer)(-14), negative[5]);
		
		integerSorter.sort(duplicates);
		assertEquals((Integer)(-30), duplicates[0]);
		assertEquals((Integer)(-30), duplicates[1]);
		assertEquals((Integer)15, duplicates[2]);
		assertEquals((Integer)28, duplicates[3]);
		assertEquals((Integer)28, duplicates[4]);
		assertEquals((Integer)45, duplicates[5]);
	}

	/**
	 * InsertionSorter tests for Student objects
	 */
	@Test
	public void testSortStudent() {
		students = new Student[7];
		students[0] = sOne;
		students[1] = sTwo;
		students[2] = sThree;
		students[3] = sFour;
		students[4] = sFive;
		students[5] = duplicateLastFive;
		students[6] = duplicateFive;
		studentSorter.sort(students);
		assertEquals(duplicateLastFive, students[0]);
		assertEquals(sFive, students[1]);
		assertEquals(duplicateFive, students[2]);
		assertEquals(sFour, students[3]);
		assertEquals(sOne, students[4]);
		assertEquals(sThree, students[5]);
		assertEquals(sTwo, students[6]);
		
		// You will also need to go back and update the existing test cases
		// since the design of the software will change to allow generic types
	}
}
