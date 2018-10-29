public class MoveHistory {
	
	private Piece piece;
	private BoardCoordinate move;
	
	public MoveHistory(Piece piece, BoardCoordinate move) {
		this.piece = piece;
		this.move = move;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public BoardCoordinate getMove() {
		return move;
	}

	public void setMove(BoardCoordinate move) {
		this.move = move;
	}
	
}
