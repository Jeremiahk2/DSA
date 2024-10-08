package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 * @param <E> Generic type to be sorted, extends Comparable to determine the order to sort in.
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {

	/** default constructor for InsertionSorter. Sets comparator to null */
	public InsertionSorter() {
		this(null);
	}
	/**
	 * Constructor, creates a comparator using the parameter to be used during sorting.
	 * @param comparator the comparator to be set as the private field.
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	/**
	 * Sorts data using the Insertion sorting algorithm
	 * @param data the data to be sorted
	 */
	public void sort(E[] data) {
		for (int i = 1; i <= data.length - 1; i++) {
			E misplaced = data[i];
			int j = i - 1;
			while (j >= 0 && compare(data[j], misplaced) > 0) {
				data[j + 1] = data[j];
				j -= 1;
			}
			data[j + 1] = misplaced;
		}
	}
}
