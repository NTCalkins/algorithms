/* Nicholas Calkins
 * October 25th, 2019
 *
 * Program for divide and conquer, classic matrix multiplication
 */

public class Classic {

	/**
	 * Multiplies two nxn matrices using classic Divide and conquer
	 * <p>
	 * This algorithm takes two nxn matrices and if n is greater than 2 
	 * and a power of 2,then it will divide the problem and use the divided
	 * solutions to construct a larger solution, which will eventually be
	 * C, an nxn matrix that is the result of A*B.
	 * <\p>
	 * 
	 * @param int[][] A, the first matrix to be multiplied
	 * @param int[][] B, the second matrix to be multiplied
	 * @param int n, the size of the two matrices
	 * @return nxn int array that is A*B
	 *
	 */
	public int[][] matrixMult(int[][] A, int[][] B,int n) {
		
		//nxn array that will eventually be returned as the result
		int[][] C = new int[n][n];

		//int to not have to keep doing n/2
		int m = n/2;

		//base case for D&C
		if (n == 2) {
			C[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0];
			C[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1];
			C[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0];
			C[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1];
		}

		//case for n > 2
		else {
			//make arrays for subdivision of A and B
			int[][] A11 = new int[m][m];
			int[][] A12 = new int[m][m];
			int[][] A21 = new int[m][m];
			int[][] A22 = new int[m][m];
			int[][] B11 = new int[m][m];
			int[][] B12 = new int[m][m];
			int[][] B21 = new int[m][m];
			int[][] B22 = new int[m][m];

			//make array subdivision for C
			int[][] C11 = new int[m][m];
			int[][] C12 = new int[m][m];
			int[][] C21 = new int[m][m];
			int[][] C22 = new int[m][m];

			int i, j;
			
			//load the two matrices with respective indices from parents
			for (i = 0; i < m; i++) {
				for (j = 0; j < m; j++) {
					A11[i][j] = A[i][j];
					B11[i][j] = B[i][j];
					A21[i][j] = A[i+m][j];
					B21[i][j] = B[i+m][j];
					A12[i][j] = A[i][j+m];
					B12[i][j] = B[i][j+m];
					A22[i][j] = A[i+m][j+m];
					B22[i][j] = B[i+m][j+m];
				}
			}

			//do the subdivisions
			C11 = matrixAdd(matrixMult(A11,B11,m), 
					matrixMult(A12,B21,m), m);

			C12 = matrixAdd(matrixMult(A11,B12,m), 
					matrixMult(A12,B22,m), m);

			C21 = matrixAdd(matrixMult(A21,B11,m), 
					matrixMult(A22,B21,m), m);

			C22 = matrixAdd(matrixMult(A21,B12,m), 
					matrixMult(A22,B22,m), m);

			//load the C matrix.
			for (i = 0; i < m; i++) {
				for (j = 0; j < m; j++) {
					C[i][j] = C11[i][j];
					C[i+m][j] = C21[i][j];
					C[i][j+m] = C12[i][j];
					C[i+m][j+m] = C22[i][j];
				}
			}
			

		}

		return C;
	}

	/**
	 * Function to add two nxn matrices together
	 * 
	 * @param int[][] A, matrix to be added
	 * @param int[][] B, matrix to be added
	 * @param int n, size of matrices to be added
	 * @return an nxn array that is A + B
	 *
	 */
	public int[][] matrixAdd(int[][] A, int[][] B, int n) {
		int[][] C = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}

		return C;
	}
}
