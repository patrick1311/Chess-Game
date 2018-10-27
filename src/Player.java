import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {
	private String color;
	private List<Piece> pieceList;
	private Stack<Piece> graveyard;
	private boolean underCheck;
	
	public Player() {
	}
	
	public void initialize(String color) {
		pieceList = new ArrayList<Piece>();
		graveyard = new Stack<Piece>();
		this.color = color;
		this.initializePieces();
	}
	
	private void initializePieces() {
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
	
	public String getColor() {
		return color;
	}
	
	public List<Piece> getPieceList() {
		return pieceList;
	}
	
	public void addToGraveyard(Piece piece) {
		graveyard.push(piece);
		removePiece(piece);
	}
	
	private void removePiece(Piece piece) {
		pieceList.remove(piece);
	}
	
	public boolean underCheck() {
		return underCheck;
	}
	
	public void setCheck(boolean underCheck) {
		this.underCheck = underCheck;
	}
}