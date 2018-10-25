import java.util.LinkedList;

public class Validator {
	public boolean isSameColor(Piece p1, Piece p2) {
		if(p1.getColor().equals(p2.getColor()))
			return true;
		return false;
	}
	
	public boolean isValidMove(LinkedList<BoardCoordinate> validMoves, BoardCoordinate move) {
		for(BoardCoordinate validMove: validMoves) {
			if(move.getX() == validMove.getX() && move.getY() == validMove.getY()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean underCheck(Player currentPlayer) {
		
	}
	
	public boolean underCheckmate(Player currentPlayer) {
		
	}
	
	public boolean isDraw() {
		
	}
}