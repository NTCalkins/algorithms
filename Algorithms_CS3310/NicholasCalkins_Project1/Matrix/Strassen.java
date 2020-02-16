/**
 * Nicholas Calkins 
 * October 25th, 2019
 *
 * Program for defining the divide and conquer Strassen mutliplication
 */

public class Strassen {
	
	/**
	 * Function that will use Strassen Matrix multiplication to get the result
	 * of A*B
	 *
	 * @param int[][] A, the first array to be multiplied
	 * @param int[][] B, the second array to be multiplied
	 * @param int n, the size of the array (nxn)
	 * @return an nxn array that is the result of A*B
	 *
	 */	
	public int[][] strassenMult(int[][] A, int [][] B, int n) {
		
		//array to hold the final result
		int[][] C = new int[n][n];

		//base case
		if (n==2) {
			C[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0];
			C[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1];
			C[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0];
			C[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1];
		}

		else {
			int m = n/2;

			//create the subdivision of the A and B arrays
                        int[][] A11 = new int[m][m];
                        int[][] A12 = new int[m][m];
                        int[][] A21 = new int[m][m];
                        int[][] A22 = new int[m][m];
                        int[][] B11 = new int[m][m];
                        int[][] B12 = new int[m][m];
                        int[][] B21 = new int[m][m];
                        int[][] B22 = new int[m][m];

			//subdivisions of the C array
                        int[][] C11 = new int[m][m];
                        int[][] C12 = new int[m][m];
                        int[][] C21 = new int[m][m];
                        int[][] C22 = new int[m][m];

			//arrays needed for the strassen matrix multiplication
			int[][] P = new int[m][m];
			int[][] Q = new int[m][m];
			int[][] R = new int[m][m];
			int[][] S = new int[m][m];
			int[][] T = new int[m][m];
			int[][] U = new int[m][m];
			int[][] V = new int[m][m];

			//integers to parse through A and B
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

			//load the arrays according to Strassen's method

			//temporary arrays to make calculating P easier
			int[][] P1 = matrixAdd(A11, A22, m);
			int[][] P2 = matrixAdd(B11, B22, m);

			P = strassenMult(P1, P2, m);

			Q = strassenMult(matrixAdd(A21,A22,m), B11, m);

			R = strassenMult(A11, matrixSub(B12, B22, m), m);

			S = strassenMult(A22, matrixSub(B21, B11, m), m);

			T = strassenMult(matrixAdd(A11, A12, m), B22, m);

			//temporary arrays to make calculating U easier
			int[][] U1 = matrixSub(A21, A11, m);
			int[][] U2 = matrixAdd(B11, B12, m);

			U = strassenMult(U1, U2, m);

			int[][] V1 = matrixSub(A12, A22, m);
			int[][] V2 = matrixAdd(B21, B22, m);
			V = strassenMult(V1, V2, m);

			//calculate the subdivisions of C
			C11 = matrixAdd(matrixSub(matrixAdd(P,S,m),T,m),V,m);
			C12 = matrixAdd(R, T, m);
			C21 = matrixAdd(Q, S, m);
			C22 = matrixAdd(matrixSub(matrixAdd(P,R,m),Q,m),U,m);

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
	 * Function to add two matrices together
	 * @param int[][] A, the first matrix to be added
	 * @param int[][] B, the second matrix to be added
	 * @param int n, the size of the matrices (nxn)
	 * @return the result of A + B
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

	/**
	 * Function to subtract two matrices
	 * @param int[][] A, the matrix that is being subtracted from
	 * @param int[][] B, the matrix that is subtracting
	 * @return the result of A - B
	 */
	public int[][] matrixSub(int[][] A, int[][] B, int n) {
		
		int[][] C = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		return C;
	}
}
