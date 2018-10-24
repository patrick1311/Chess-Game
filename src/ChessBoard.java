
public class ChessBoard {
	
	public static final int TILE_SIZE = 80;
	public static final int BOARD_LENGTH = 8;
	
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
