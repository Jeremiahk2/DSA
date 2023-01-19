package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> implements Sorter<E> {

    private Comparator<E> comparator;
    
    public SelectionSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   

    public void sort(E[] data) {
        for (int i = 0; i <= data.length - 1; i++) {
        	int min = i;
        	for (int j = i + 1; j <= data.length - 1; j++) {
        		if (data[j].compareTo(data[min]) < -1) {
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
    
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
}