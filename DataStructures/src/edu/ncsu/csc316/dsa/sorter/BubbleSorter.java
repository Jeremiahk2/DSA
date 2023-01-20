/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * class for using a bubble sorting algorithm for sorting data. Utilizes a generic type along with the generic types comparator
 * @author Jeremiah Knizley
 * @param <E> the generic type, must be Comparable in order for sorting to be accurate.
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {

	
	/**
	 * Creates a BubbleSorter with a custom comparator. If comparator is null, natural ordering is used (compareTo).
	 * @param comparator the comparator to be used during sorting
	 */
	public BubbleSorter(Comparator<E> comparator) {
        super(comparator);
    }
	
	/**
	 * Constructs a bubble sorter with Natural Order paramater (compareTo)
	 */
	public BubbleSorter() {
		super(null);
	}
	/**
	 * Sorts the values using the bubble sorting algorithm.
	 * @param data the data to be sorted
	 */
	@Override
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i <= data.length - 1; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
		
	}

}
