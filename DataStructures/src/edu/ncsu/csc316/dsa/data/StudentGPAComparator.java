package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Jeremiah Knizley
 * @author Dr. King
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order
	 * @param one the first student to be compared. 
	 * @param two the second student being compared to the first student
	 * @return int -1 if student one comes before student two, 0 if they are equal, and 1 if student one comes after student two
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getGpa() < two.getGpa()) {
			return 1;
		}
		else if (one.getGpa() > two.getGpa()) {
			return -1;
		}
		else {
			return one.compareTo(two);
		}
	}

}
