import java.util.LinkedList;

public class Player {
	private String color;
	private LinkedList<Piece> pieceList;
	
	public Player(String color) {
		this.color = color;
		pieceList = new LinkedList<Piece>();
		pieceList.add(new King(this, color));
		pieceList.add(new Queen(this, color));
		for(int i = 0; i < 2; i++) {
			pieceList.add(new Rook(this, color));
			pieceList.add(new Bishop(this, color));
			pieceList.add(new Knight(this, color));
		}
		for(int i = 0; i < 8; i++) {
			pieceList.add(new Pawn(this, color));
		}
	}
	
	public LinkedList<Piece> getPieceList() {
		return pieceList;
	}
}
