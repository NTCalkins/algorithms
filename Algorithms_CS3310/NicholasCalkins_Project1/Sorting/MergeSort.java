/* Nicholas Calkins
 * October 25th, 2019
 *
 * Implementation of mergesort in Java
 *
 */

import java.lang.Math;

public class MergeSort {


	/**
	 * Function to sort an array using the mergesort algorithm
	 *
	 * @param int[] array, the array to be sorted
	 * @param int n, the number of items in the array
	 * 
	 */
	void mergeSort(int[] array, int n) {

		if (n>1) {
			int h = n/2;
			int m = n - h;
			int[] arrayLow = new int[h];
			int[] arrayHigh = new int[m];
			
			for (int i = 0; i < h; i++) {
				arrayLow[i] = array[i];
			}
			for (int j = h; j < n; j++) {
				arrayHigh[j-h] = array[j];
			}
			
			mergeSort(arrayLow, h);
			mergeSort(arrayHigh, m);
			mergeArray(h, m, arrayLow, arrayHigh, array);
		}	

	}

	/**
	 * Function to merge two arrays, putting them into sorted order.
	 * 
	 * @param int h, the size of arrayLow
	 * @param int m, the size of arrayHigh
	 * @param int[] arrayLow, one array to merge
	 * @param int[] arrayHigh, another array to merge
	 * @param int[] array, the solution array
	 */
	void mergeArray(int h, int m, int[] arrayLow, int[] arrayHigh, 
			int[] array ) {

		//iterators for low to mid, mid+1 to high, temp array,
		//respectively.
		int i = 0;
		int j = 0;
		int k = 0;

		//while both of the sublists remain unexhausted
		while (i < h && j < m) {
			//case for arrayLow having a smaller element
			if (arrayLow[i] <= arrayHigh[j]) {
				array[k] = arrayLow[i];
				i++;
			}
			//case for arrayHigh having a smaller element
			else {
				array[k] = arrayHigh[j];
				j++;
			}
			//move along in the temp array
			k++;
		}

		//case for arrayLow being unexhausted
		while (i < h) {
			array[k] = arrayLow[i];
			k++;
			i++;
		}
		//case for arrayHigh being unexhausted
		while (j < m) {
			array[k] = arrayHigh[j];
			k++;
			j++;
		}
	}
}
