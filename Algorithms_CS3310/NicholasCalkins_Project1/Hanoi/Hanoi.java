/* Nicholas Calkins
 * October 25th, 2019
 * 
 * The algorithm for solving the Tower of Hanoi problem using
 * the paradigm of divide and conquer
 *
 */

public class Hanoi {

	public static int moveNum = 0;
	
	/**
	 * This function will move a desired tower with largest disc size of
	 * discNum from rod A to rod C, using rod B as an intermediary if
	 * necessary.
	 * @param int discNum, the number disc we are trying to get to the solution rod
	 * @param String A, the name of the rod we start on.
	 * @param String B, the name of the rod that is used as an intermediary
	 * @param String C, the name of the rod that is the solution rod
	 */
	public void hanoiOperation(int discNum, String A, String B, String C) {
	
		if (discNum == 1) {
			System.out.print("Move #" + ++moveNum + ": ");
			System.out.println("Moving disc #" + discNum +
					   " from rod " + A + " to rod " + C);
		}
		else {
			//have to move every single disc off of discNum
			hanoiOperation(discNum-1, A, C, B);
			//now, we can move the discNum onto the solution rod
			System.out.print("Move #" + ++moveNum + ": ");
			System.out.println("Moving disc #" + discNum +
					   " from rod " + A + " to rod " + C);
			//Now to move the next disc onto the solution rod.
			hanoiOperation(discNum-1, B, A, C);
		}
	}
}
