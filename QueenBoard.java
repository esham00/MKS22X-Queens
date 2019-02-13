public class QueenBoard {
    //variable
    private int [][] board;
    //constructor: initizalizing board
    public QueenBoard(int size) {
	board = new int[size][size];
	for(int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		board[i][j] = 0;
	    }
	}
    }
    //toString, 0s print for no solutions , -1 print as Qs, rest prints _
    public String toString() {
	String output = "";
	    //go down the rows
	    for (int i = 0; i < board.length; i++) {
		//go down the columns
		for (int j = 0; j < board[0].length; j++) {
		    //if board[i][j] < 0, it indicates a queen is there
		    if (board[i][j] < 0) {
			output += " Q";
		    }
		    //if a queen is not there print an underscore
		    else {
			output += " _";
		    }
		}
		output += "\n";
	    }
	return output;
    }
    //for debugging add and remove: follows toString but prints the number instead
    public String toStringDebug() {
	String output = "";
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		output += "  " + board[i][j];
	    }
	    output += "\n";
	}
	return output;
    }
    //adding queen at xy coordinate		
    private boolean addQueen(int r, int c) {
	//diagonal down
	int x1 = r+1;
	//diagonal up 
	int x2 = r-1;
	//works only if there isnt a queen attacking or there 
	if (board[r][c] == 0) {
	    for(int i = c+1; i < board[0].length; i++) {
		//make all the columns to the right +1
		board[r][i] += 1;
		if (x1 < board.length) {
		    //make all the columns diagonal down +1 until it reaches the end
		    board[x1][i]+=1;
		    x1++;
		}
		if (x2 >= 0) {
		    //make all the columns diagonal up +1 until it reaches the end
		    board[x2][i]+=1;
		    x2--;
		}
	    }
	    //making the xy cor of the queen -1
	    board[r][c] = -1;
	    return true;
	} else {
	    return false;
	}
    }
    //removeQueen
    private boolean removeQueen(int r, int c) {
	//diagonal down
	int x1 = r+1;
	//diagonal up
	int x2 = r-1;
	//remove only works if there is a queen there
	if (board[r][c] == -1) {
	    for(int i = c+1; i < board[0].length; i++) {
		//remove one to all spaces to the right of the queen cause there isn't a queen attacking there
		board[r][i] -= 1;
		if(x1 < board.length) {
		    //remove one from all spaces diagonal down
		    board[x1][i] -= 1;
		    x1++;
		}
		if (x2 >= 0) {
		    //remove one from all spaces diagonal up
		    board[x2][i] -= 1;
		    x2--;
		}
	    }
	    //set the xy there as 0 (since the queen was placed there bc nothing was attacking it so the original must be 0)
	    board[r][c] = 0;
	    return true;
	} else {
	    return false;
	}
    }
    //in-class solution
    private boolean solveH(int c) {
	//if you reached the end of the board, then it was a success
	if (c == board.length) {
	    return true;
	} else {
	    for (int r = 0; r < board.length; r++) {
	        //see if you can add queen at the xy
		//if you can, then move onto the next column (for testing all rows)
		if (addQueen(r,c)) {
		    if (solveH(c+1)) {
			//solution  = true;
			return true;
		    }
		    //if it doesn't work then you have to go back a column 
		    removeQueen(r,c);
		}
	    }
	}
	    //if you never reach the end then there's no solution
	return false;
    }
    public boolean solve() {
	//illegal state exception for starting w/ non zeros
	for (int i = 0; i < board.length; i++){
	    if (board[i][0] != 0) {
		throw new IllegalStateException();
	    }
	}
	//solve helper
	return solveH(0);
    }
    private int countSolutionsH(int c) {
	//if reached the end of the board then you found a solution
	if (c == board.length) {
	    return 1;
	}
	//keeping track of the solutions
	int solutions = 0;
	//testing the rows of each column
	for(int r = 0; r < board.length; r++) {
	    //if it is possible to add queen then update # of solutions 
	    if (addQueen(r, c)) {
		solutions += countSolutionsH(c+1);
	    }
	    //move down the row of solutions after adding
	    removeQueen(r, c);
	}
	return solutions;
    }
    public int countSolutions() {
	//illegal state exception for starting w/ non zeros
	for (int i = 0; i < board.length; i++){
	    if (board[i][0] != 0) {
		throw new IllegalStateException();
	    }
	}
        return countSolutionsH(0);
    }	
    public static void main(String[] args) {
	QueenBoard a = new QueenBoard(4);
	QueenBoard b = new QueenBoard(8);
	 // addQueen(0,0);
	 // addQueen(2,1);
	 // removeQueen(2,1);
        a.solve();
	System.out.println(a.toString());
        System.out.println(b.countSolutions());
    }
}
