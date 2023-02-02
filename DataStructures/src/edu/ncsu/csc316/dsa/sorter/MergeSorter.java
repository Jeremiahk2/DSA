package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		if (data.length >= 2) {
			int mid = data.length / 2;
			E[] left = Arrays.copyOfRange(data, 0, mid);
			E[] right = Arrays.copyOfRange(data, mid, data.length);
			
			sort(left);
			sort(right);
			
			merge(left, right, data);
		}
	}
	
	private void merge(E[] left, E[] right, E[] original) {
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex + rightIndex < original.length) {
			if (rightIndex == right.length || leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0)  {
				original[leftIndex + rightIndex] = left[leftIndex];
				leftIndex = leftIndex + 1;
			}
			else {
				original[leftIndex + rightIndex] = right[rightIndex];
				rightIndex = rightIndex + 1;
			}
		}
	}
}