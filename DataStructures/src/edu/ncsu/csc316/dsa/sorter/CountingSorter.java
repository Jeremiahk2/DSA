package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	@Override
	public void sort(E[] values) {
		//Find the min and the max elements in the input data
		int min = values[0].getId();
		int max = values[0].getId();
		for (int i = 0; i <= values.length - 1; i++) {
			min = Math.min(values[i].getId(), min);
			max = Math.max(values[i].getId(), max);
		}
		//Calculate the range of the elements
		int k = max - min + 1;
		
		int[] b = new int[k];
		for (int i = 0; i <= values.length - 1; i++) {
			b[values[i].getId() - min] = b[values[i].getId() - min] + 1;
		}
		
		for (int i = 1; i <= k - 1; i++) {
			b[i] = b[i - 1] + b[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] f = (E[])(new Identifiable[ values.length ]);
		
		for (int i = values.length - 1; i >= 0; i--) {
			f[b[values[i].getId() - min] - 1] = values[i];
			b[values[i].getId() - min] = b[ values[i].getId() - min] - 1;
		}
		
		for (int i = 0; i < values.length; i++) {
			values[i] = f[i];
		}
	}
}
