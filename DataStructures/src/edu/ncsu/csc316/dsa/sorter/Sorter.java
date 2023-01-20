package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @param <E> the generic type of object to be sorted. 
 */
public interface Sorter<E> {
	
	/**
	 * Sorts an array of integer values.
	 * @param values the values to be sorted
	 */
	void sort(E[] data);
}
