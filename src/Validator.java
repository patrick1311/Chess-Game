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
		return false;//King is in validMove of enemy piece?
	}
	
	public boolean underCheckmate(Player currentPlayer) {
		return false;//If currentPlayer's King is under check & no validMoves 
		//for any of player's pieces
	}
	
	public boolean isDraw() {
		return false;//stalemate
		//fifty-move rule
		/*dead position? no sequence of legal moves can lead to checkmate, 
		 * most commonly when neither player has sufficient 
		 * material to checkmate the opponent.
		 */
	}
}