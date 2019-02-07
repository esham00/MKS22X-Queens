# MKS22X-Queens

public class QueenBoard {
    private int [][] board;
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
	}
	return output;
    }
    private boolean addQueen(int r, int c) {
	if (board[r][c] == 0) {
	    board[r][c] = -1;
	    return true;
	} else {
	    return false;
	}
    }
    private boolean removeQueen(int r, int c) {
        for(int i  = 0; i < 
    }
}
