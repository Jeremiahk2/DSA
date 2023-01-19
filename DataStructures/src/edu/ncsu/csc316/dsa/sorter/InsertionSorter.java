package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 * @param <E> Generic type to be sorted, extends Comparable to determine the order to sort in.
 */
public class InsertionSorter<E extends Comparable<E>> implements Sorter<E> {

	/** the comparator object to be used for sorting */
	private Comparator<E> comparator;
	/** default constructor for InsertionSorter. Sets comparator to null */
	public InsertionSorter() {
		this(null);
	}
	/**
	 * Constructor, creates a comparator using the parameter to be used during sorting.
	 * @param comparator the comparator to be set as the private field.
	 */
	public InsertionSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}
	/**
	 * sets the comparator to the parameter, unless it is null, in which case the natural order is used.
	 * @param comparator the comparator to be set
	 */
	private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }
	/**
	 * Sorts data using the Insertion sorting algorithm
	 * @param data the data to be sorted
	 */
	public void sort(E[] data) {
		for (int i = 1; i <= data.length - 1; i++) {
			E misplaced = data[i];
			int j = i - 1;
			while (j >= 0 && comparator.compare(data[j], misplaced) > 0) {
				data[j + 1] = data[j];
				j -= 1;
			}
			data[j + 1] = misplaced;
		}
	}
	
	/**
	 * Inner class for maintaining the natural order used by InsertionSorter.
	 * @author Jeremiah Knizley
	 *
	 */
	private class NaturalOrder implements Comparator<E> {
		/**
		 * Sets the natural ordering to compareTo from Comparable
		 * First and second are arbitrary except that if first comes before, a -1 is returned, if first comes after, a 1 is returned.
		 * @param first the first object to be compared
		 * @param second the second object to be compared
		 * @return the int gained from using first's compareTo method with second as the parameter.
		 */
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}
}
