import java.util.LinkedList;
import java.util.Stack;

public class Player {
	private String color;
	private LinkedList<Piece> pieceList;
	private Stack<Piece> graveyard;
	private boolean underCheck;
	
	public Player(String color) {
		this.color = color;
		graveyard = new Stack<Piece>();
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
	
	public String getColor() {
		return color;
	}
	
	public LinkedList<Piece> getPieceList() {
		return pieceList;
	}
	
	public void addToGraveyard(Piece piece) {
		graveyard.push(piece);
	}
	
	public void removePiece(Piece piece) {
		pieceList.remove(piece);
	}
	
	public boolean underCheck() {
		return underCheck;
	}
	
	public void setCheck(boolean underCheck) {
		this.underCheck = underCheck;
	}
	
	/*
	public Piece selectTile(BoardCoordinate tile) {
		Piece selectedPiece;
		
		display.clearHighlights();
		//code
		//
		return selectedPiece;
	}
	
	public void move(Piece piece, BoardCoordinate destination) {
		
	}*/
}
