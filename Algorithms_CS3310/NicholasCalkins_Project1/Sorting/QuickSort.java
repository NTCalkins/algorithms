/* Nicholas Calkins
 * October 7th, 2019
 * 
 * Implementation of Quicksort from psuedocode
 *
 */

import java.util.Random;

class QuickSort {
	
	/**
	 * Function to sort an array using the quicksort algorithm
	 *
	 * @param int[] array, the array to be sorted
	 * @param int low, the index of the lowest element
	 * @param int high, the index of the highest element
	 * @return nothing, but array is now sorted
	 */
	void quicksort(int array[], int low, int high) {
		
		if (low < high) {
			//parition, get the pivotpoint
			int pivotpoint = partition(array, low, high);
			//operate on the two new paritions
			quicksort(array,low, pivotpoint-1);
			quicksort(array,pivotpoint+1, high);
		}
	}

	/**
	 * Function to parition an array
	 *
	 * @param int[] arr, the array to be partitioned
	 * @param int low, the lowest index of the array
	 * @param int high, the highest index of the array
	 * @return the index of the pivot of the partition
	 */
	int partition(int arr[], int low, int high) {
		
		int i, j, temp, pivotpoint;
		
		//choose the first value as the pivot
		int pivot = arr[low];
		
		//start j at low
		j = low;
		
		for (i = low+1; i <= high; i++) {
			//case to move an item before the pivot
			if (arr[i] < pivot) {
				j++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		//put the pivot in its proper place
		pivotpoint = j;
		temp = arr[low];
		arr[low] = arr[pivotpoint];
		arr[pivotpoint] = temp;

		return pivotpoint;
	}
}
