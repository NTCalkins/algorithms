/* Nicholas Calkins
 * October 25th, 2019
 *
 * Main driver for testing quicksort vs mergesort on variable inputs of size n,
 * where n is the size of the list.
 *
 *
 *
 */

import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.util.concurrent.TimeUnit;


public class SortTest {

	/**
	 * Function to test the two sorts against each other
	 *
	 *
	 */	
	public static void main(String args[]) {
	
		//object to generate random integers
		Random random = new Random();

		//objects to sort the arrays
		MergeSort mergesort = new MergeSort();
		QuickSort quicksort = new QuickSort();

		//collect input from user
		Scanner s = new Scanner(System.in);
		System.out.println("How many elements to sort?: ");
		int n = s.nextInt();

		int[] qArr = new int[n];
		int[] mArr = new int[n];

		//load the two arrays with random integers, up to the max value
		for (int i = 0; i < n; i++) {
			qArr[i] = random.nextInt(Integer.MAX_VALUE);
			mArr[i] = qArr[i];
		}
		System.out.println();
		
		long mergesortStart = System.nanoTime();
		//give mergesort the array but also the number of elements in it
		mergesort.mergeSort(mArr, n);
		long mergesortEnd = System.nanoTime();
		long mergesortTime = mergesortEnd - mergesortStart;
	
		long quicksortStart = System.nanoTime();
		//give quicksort the array, the low and high index
		quicksort.quicksort(qArr, 0, n-1);	
		long quicksortEnd = System.nanoTime();
		long quicksortTime = quicksortEnd - quicksortStart;

		System.out.println();

		//printing times for sorts to operate on the same list
		System.out.println("Mergesort Time in milliseconds: " + 
				   mergesortTime/1000000);

		System.out.println("Quicksort time in milliseconds: " +
				   quicksortTime/1000000);
	}

}
