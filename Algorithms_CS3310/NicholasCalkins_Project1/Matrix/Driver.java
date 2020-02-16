/**
 * Nicholas Calkins
 * October 25th, 2019
 *
 * Driver for comparing the two matrix multiplications
 */

import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.Random;


public class Driver {
	/**
	 * Main method for driving the matrix multiplications
	 * 
	 * Remember to enter a number right after "java Driver"
	 *
	 */	
	public static void main(String args[]) {

		//object to generate random numbers		
		Random random = new Random();

		Scanner s = new Scanner(System.in);

		System.out.println("Type in n, which will generate 2 nxn matrices:");
		int n = s.nextInt();

		int[][] A = new int[n][n];
		int[][] B = new int[n][n];

		//generates two random, distinct nxn matrices that contain
		//0-9 in every cell.
		for (int i = 0; i < n; i++ ){
			for (int j = 0; j < n; j++) {
				A[i][j] = random.nextInt(9);
				B[i][j] = random.nextInt(9);
			}
		}

/*
		System.out.println("Matrix A:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();

		System.out.println("Matrix B:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(B[i][j] + " ");
			}
			System.out.println();
		}
*/

		Classic classic = new Classic();
		Strassen strassen = new Strassen();

		//Find A*B using Strassen, record the time
		long sStart = System.currentTimeMillis();
		int[][] C1 = strassen.strassenMult(A,B,n);
		long sEnd = System.currentTimeMillis();

		//find A*B using classic D&C Matrix multiplication, record time
		long cStart = System.currentTimeMillis();
		int[][] C2 = classic.matrixMult(A,B,n);
		long cEnd = System.currentTimeMillis();

		//this is left in the program as an easy way to prove correctness
/*
		System.out.println("Output matrix of A*B in Strassen:");		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(C1[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("Output matrix of A*B in classic:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(C2[i][j] + " ");
			}
			System.out.println();
		}
*/
		//tell the user the speed of both algorithms
		System.out.println("Time to do classic D&C matrix mult:");
		System.out.println(cEnd - cStart + " milliseconds\n");

		System.out.println("Time to do strassen D&C matrix mult:");
		System.out.println(sEnd - sStart + " milliseconds\n");

	}
}
