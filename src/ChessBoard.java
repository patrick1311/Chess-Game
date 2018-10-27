public class ChessBoard {
	public static final int TILE_SIZE = 80;
	public static final int BOARD_LENGTH = 8;
	
	private Piece board[][] = new Piece[8][8];
	
	public Piece[][] getBoard() {
		return board;
	}
	
	public Piece getPiece(int x, int y) {
		if(x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
			return board[x][y];
		}
		return null;
	}
	
	public void move(Piece piece, BoardCoordinate tile) {
		int x = tile.getX();
		int y = tile.getY();
		Piece tilePiece = getPiece(x, y);
		
		if(tilePiece != null) {
			tilePiece.getPlayer().addToGraveyard(tilePiece);
		}
		board[piece.getCoordinate().getX()][piece.getCoordinate().getY()] = null;
		board[x][y] = piece;
		piece.setCoordinate(tile);
		if(piece instanceof Pawn) {
			((Pawn)piece).setFirstMove(false);
		}
	}
}