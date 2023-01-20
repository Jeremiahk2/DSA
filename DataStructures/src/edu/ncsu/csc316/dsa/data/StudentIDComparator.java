package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Jeremiah Knizley
 * @author Dr. King
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on ID in ascending order
	 * @param one the first student to be compared. 
	 * @param two the second student being compared to the first student
	 * @return int -1 if student one comes before student two, 0 if they are equal, and 1 if student one comes after student two
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getId() < two.getId()) {
			return -1;
		}
		else if (one.getId() > two.getId()) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
