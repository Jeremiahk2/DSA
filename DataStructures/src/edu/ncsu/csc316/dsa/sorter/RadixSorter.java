package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Sorts values using RadixSort algorithm
	 * @param values the values to be sorted
	 */
	@Override
	public void sort(E[] values) {
		//Find the largest value in the input data
		int k = 0;
		for (int i = 0; i <= values.length - 1; i++) {
			k = Math.max(k, values[i].getId());
		}
		//Determine how many digits are in the largest value
		int x = (int)Math.ceil( Math.log10(k + 1) / Math.log10(10));
		
		int p = 1;
		for (int j = 1; j <= x; j++) {
			int[] b = new int[10];
			for (int i = 0; i <= values.length - 1; i++) {
				b[ (values[i].getId() / p) % 10 ] = b[(values[i].getId() / p) % 10] + 1;
			}
			
			for (int i = 1; i <= 9; i++) {
				b[i] = b[i - 1] + b[i];
			}
			
			@SuppressWarnings("unchecked")
			E[] f = (E[])(new Identifiable[ values.length ]);
			
			for (int i = values.length - 1; i >= 0; i--) {
				f[b[(values[i].getId() / p) % 10] - 1] = values[i];
				b[(values[i].getId() / p) % 10] = b[(values[i].getId() / p) % 10] - 1;
			}
			
			for (int i = 0; i <= values.length - 1; i++) {
				values[i] = f[i];
			}
			
			p = p * 10;
		}
	}
}
