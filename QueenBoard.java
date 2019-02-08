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
    private static boolean addQueen(int r, int c) {
	if (board[r][c] == 0) {
	    board[r][c] = -1;
	    for(int i = r; i < board.length; i++) {
		board[i][c] += 1;
		for (int x = r; x < 0; x--) {
		    for (int j = c; j < board[r].length; j++) {
			board[i][j] += 1;
			board[x][j] += 1;
		    }
		}
	    }
	    return true;
	} else {
	    return false;
	}
    }
    private static boolean removeQueen(int r, int c) {
	if (board[r][c] == -1) {
	    board[r][c] = 0;
	    // for(int i = c; i < board[r].length; i++) {
	    //     board[r][i] -= 1;
	    // }
	    for(int i = r; i < board.length; i++) {
		board[i][c] -= 1;
		for (int x = r; x < 0; x--) {
		    for (int j = c; j < board[r].length; j++) {
			board[i][j] -= 1;
			board[x][j] -= 1;
		    }
		}
	    }
	    return true;
	} else {
	    return false;
	}
    }
    private static void solve(int r, int c, int worked) {
	if (worked != board.length) {
	    if (addQueen(r,c)) {
		addQueen(r,c);
		solve(r, c+1, worked+1);
	    } else {
		removeQueen(r,c);
		solve(r+1,c, worked);
	    }
	}
    }
    public boolean solve() {
	solve(0, 0, 0);
	return true;
    }
    public static void main(String[] args) {
	QueenBoard a = new QueenBoard(3);
	a.solve();
	System.out.println(a);
    }
}
