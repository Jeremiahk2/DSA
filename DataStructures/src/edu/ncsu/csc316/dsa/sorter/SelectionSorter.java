package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> implements Sorter<E> {

	/**
     * Creates a new selection sorter with the given custom comparator.
     * @param comparator the comparator to be used during sorting
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    /**
     * Constructs a selection sorter using a Natural Ordering comparator (compareTo)
     */
    public SelectionSorter() {
    	super(null);
    }
    
    /**
     * sorts the data using the comparator
     * @param data the data to be sorted
     */
    public void sort(E[] data) {
        for (int i = 0; i <= data.length - 1; i++) {
        	int min = i;
        	for (int j = i + 1; j <= data.length - 1; j++) {
        		if (compare(data[j], data[min]) < 0) {
        			min = j;
        		}
        	}
        	if (i != min) {
        		E x = data[i];
        		data[i] = data[min];
        		data[min] = x;
        	}
        }
    }
}