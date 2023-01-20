package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

//import edu.ncsu.csc316.dsa.sorter.SelectionSorter.NaturalOrder;

/**
 * Abstract super class for comparison sorters. Allows the sharing of common methods and data between comparison sorting algorithms
 * @author Jeremiah Knizley
 *
 * @param <E> abstract data type that are comparable
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** the comparator to use during sorting */
    private Comparator<E> comparator;
    
    /**
     * constructor for AbstractComparisonSorter. Sets the comparator to the parameter
     * @param comparator the comparator to use during sorting
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * sets the comparator to the parameter. If the comparator is null, the NaturalOrder is used
     * @param comparator the comparator to be used during sorting
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * class for maintaining the natural order for comparison based sorting
     * @author Jeremiah Knizley
     *
     */
    private class NaturalOrder implements Comparator<E> {
    	/**
    	 * compares first with second by using compareTo (the natural ordering)
    	 * @param first the first object to be compared
    	 * @param second the second object that is compared with the first.
    	 * @return 0 if the two objects are equal, -1 if first comes before second, and 1 if first comes after second
    	 */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * compares data1 and data2 using the comparator
     * @param data1 the first object to be compared
     * @param data2 the second object to be compared
     * @return 0 if the two pieces of data are equal, -1 if data1 comes before data2, 1 if data1 comes after data 2
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}