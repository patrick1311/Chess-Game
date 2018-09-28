public class ChessBoard {
	
	public static final int TILE_SIZE = 80;
	
	private Piece board[][] = new Piece[8][8];
	
	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public Piece[][] getBoard() {
		return board;
	}
	
	public Piece getPiece(int x, int y) {
		return board[x][y];
	}
	
}
