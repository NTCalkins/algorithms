/* Nicholas Calkins
 * October 25th, 2019
 *
 * Program to test the Hanoi algorithm.
 *
 */

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HanoiTester {

	/**
	 * Main driver to test the Hanoi function
	 */
	public static void main(String args[]) {
	
		Scanner s = new Scanner(System.in);
		System.out.println("How many discs to use in tower of Hanoi?");

		int n = s.nextInt();

		Hanoi hanoi = new Hanoi();

		long start = System.nanoTime();
		hanoi.hanoiOperation(n,"start","helper","final");
		long end = System.nanoTime();
		long time = end - start;

		double minutes = ((double) time/1000000) / 60000;

		System.out.println();

		System.out.println("Time to do Tower of Hanoi for " + n
				   + " elements: " + time/1000000
				   + " milliseconds");
		System.out.println("Time in minutes: " + minutes);
	}
}
