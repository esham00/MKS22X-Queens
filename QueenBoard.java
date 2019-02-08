public class QueenBoard {
    private static int [][] board;
    public QueenBoard(int size) {
	board = new int[size][size];
	for(int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		board[i][j] = 0;
	    }
	}
    }
    public String toString() {
	String output = "";
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		if (board[i][j] < 0) {
		    output += " Q";
		} else {
		    output += " _";
		}
	    }
	    output += "\n";
	}
	return output;
    }
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
		
    private static boolean addQueen(int r, int c) {
	int x1 = r;
	int x2 = r;
	if (board[r][c] == 0) {
	    for(int i = c+1; i < board[0].length; i++) {
		board[r][i] += 1;
		if (x1 < board.length) {
		    board[x1][i]+=1;
		    x1++;
		}
		if (x2 >= 0) {
		    board[x2][i]+=1;
		    x2--;
		}
	    }
	    board[r][c] = -1;
	    return true;
	} else {
	    return false;
	}
    }
    private static boolean removeQueen(int r, int c) {
	int x1 = r;
	int x2 = r;
	if (board[r][c] == -1) {
	    for(int i = c; i < board[0].length; i++) {
		board[r][i] -= 1;
		if(x1 < board.length) {
		    board[x1][i] -= 1;
		    x1++;
		}
		if (x2 >= 0) {
		    board[x2][i] -= 1;
		    x2--;
		}
	    }
	    board[r][c] = 0;
	    return true;
	} else {
	    return false;
	}
    }
    private static boolean solve(int r, int c) {
	if (c < board[0].length) {
	    if (addQueen(r,c)) {
		solve(0, c+1);
	    } else {
		if (r < board.length-1) {
		    removeQueen(r,c);
		    solve(r+1,c);
		}
	    }
	}
	return true;
    }
    public boolean solve() {
	if (solve(0, 0)) {
	    solve(0,0);
	    return true;
	} else {
	    return false;
	}
    }
    public static void main(String[] args) {
	QueenBoard a = new QueenBoard(4);
	// addQueen(0,0);
	// addQueen(2,3);
	// removeQueen(2,1);
        a.solve();
	System.out.println(a.toStringDebug());
    }
}
